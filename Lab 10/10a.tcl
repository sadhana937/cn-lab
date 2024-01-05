# a. Implement three nodes point – to – point network with duplex links between them. Set the queue size, vary the bandwidth and find the number of packets dropped.

# Create Simulator
set ns [new Simulator]

#open trace and nam file
set ntrace [open 10a.tr w]
$ns trace-all $ntrace
set namfile [open 10a.nam w]
$ns namtrace-all $namfile

#finish procedure
proc Finish {} {
global ns ntrace namfile
$ns flush-trace
close $ntrace
close $namfile

exec nam 10a.nam &
exec echo "The number of packets dropped is " &
exec grep -c "^d" 10a.tr &
exit 0
}

# create 3 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$n0 label "TCP Source"
$n2 label "Sink"

$ns color 1 blue

$ns duplex-link $n0 $n1 10Mb 10ms DropTail
$ns duplex-link $n1 $n2 10Mb 10ms DropTail



$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n1 $n2 orient right

$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10

# Transport layer connection
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $n2 $sink
$ns connect $tcp $sink 

# Application layer traffic
set cbr [new Application/Traffic/CBR]
$cbr set type_ CBR
$cbr set packetSize_ 100
$cbr set rate_ 1Mb
$cbr set random_ false
$cbr attach-agent $tcp

$tcp set class_ 1


$ns at 0.0 "$cbr start"
$ns at 5.0 "Finish"

$ns run
