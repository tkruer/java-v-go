package main

import (
	"fmt"
	"sync"
	"time"
)

func doWork() {
	time.Sleep(2 * time.Millisecond) // Simulate some work
}

func main() {
	numRoutines := 10000
	start := time.Now()

	var wg sync.WaitGroup
	wg.Add(numRoutines)
	for i := 0; i < numRoutines; i++ {
		go func() {
			defer wg.Done()
			doWork()
		}()
	}

	wg.Wait()
	elapsed := time.Since(start)
	fmt.Printf("Go Routines: Completed %d routines in %s\n", numRoutines, elapsed)
}
