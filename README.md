# Smarter-Priority-Queue-SPQ-ADT-Implementation

This repository contains the implementation of a Smarter Priority Queue (SPQ) Abstract Data Type (ADT) using a parameterized heap. The SPQ is designed to support both min-heap and max-heap configurations with dynamic adaptability and efficient management.

## Project Overview

The SPQ utilizes a dynamic array for the heap implementation, allowing it to flexibly handle operations as either a min-heap or max-heap based on the current configuration. This dual functionality is pivotal in applications requiring priority assessments from both ends of the spectrum.

### Key Features

- **Dual Heap Configuration**: Seamlessly transitions between min-heap and max-heap configurations.
- **Dynamic Array Implementation**: Custom extendable array to handle dynamic data sets.
- **Adaptive Operations**: Includes `toggle()`, `insert()`, `removeTop()`, and other essential operations, enabling switching between modes and dynamic data handling.

### Core Components

- `Entry`: Manages data entries within the queue, associating data with priorities and managing their positions within the array for efficient access.
- `ExpandingArray`: Facilitates the dynamic resizing of the array, ensuring efficient memory usage and allocation.
- `PriorityQueueHeap`: Acts as the main class, implementing various priority queue operations while maintaining heap properties regardless of the current heap type.

## Implementation

The SPQ ADT is built on a parameterized heap structure that supports extensive manipulation of data with optimized operations for insertion, deletion, and access based on the heap type.

### Implemented Methods

- `toggle()`: Alters the heap's configuration between minimum and maximum orientations.
- `insert(k, v)`: Adds a new key-value pair and restructures the heap accordingly.
- `removeTop()`: Extracts the highest or lowest element based on the heap's current state.
- `replaceKey(e, k)`, `replaceValue(e, v)`: Modifies existing entries in the heap to maintain order and integrity.

## Usage

Clone this repository and integrate it into your Java development environment to leverage the SPQ in your applications.
