# b. Implement transmission of ping messages/trace route over a network topology consisting of 6 nodes and find the number of packets dropped due to congestion

# create Simulator
set ns [new Simulator]

# using colors to differentiate the traffic
$ns color 1 Blue
$ns color 2 Red

# open trace and nam trace file
set ntrace [open 10b.tr w]
$ns trace-all $ntrace
set namfile [open 10b.nam w]
$ns namtrace-all $namfile

proc Finish {} {
global ns ntrace namfile

$ns flush-trace
close $ntrace
close $namfile

exec nam 10b.nam &
puts " The number of ping packets dropped are "
exec grep "^d" 10b.tr | cut -d " " -f 5 | grep -c "ping" &
exit 0
}

# create 6 nodes
for {set i 0} {$i < 6} {incr i} {
set n($i) [$ns node]
}

for {set j 0} {$j < 5} {incr j} {
$ns duplex-link $n($j) $n([expr ($j + 1)]) 0.1Mb 10ms DropTail
}

Agent/Ping instproc recv {from rtt} {
$self instvar node_
puts "node [$node_ id] received ping answer from $from with round trip time $rtt ms"
}

set p0 [new Agent/Ping]
$p0 set class_ 1
$ns attach-agent $n(0) $p0
set p1 [new Agent/Ping]
$p1 set class_ 1
$ns attach-agent $n(5) $p1
$ns connect $p0 $p1

$ns queue-limit $n(2) $n(3) 2
$ns duplex-link-op $n(2) $n(3) queuePos 0.5

set tcp [new Agent/TCP]
$tcp set class_ 2
$ns attach-agent $n(2) $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n(4) $sink
$ns connect $tcp $sink

set cbr [new Application/Traffic/CBR]
$cbr set packetSize 500
$cbr set rate_ 1Mb
$cbr attach-agent $tcp

$ns at 0.2 "$p0 send"
$ns at 0.4 "$p1 send"
$ns at 0.4 "$cbr start"
$ns at 0.8 "$p0 send"
$ns at 1.0 "$p1 send"
$ns at 1.2 "$cbr stop"
$ns at 1.4 "$p0 send"
$ns at 1.6 "$p1 send"
$ns at 1.8 "Finish"

$ns run

