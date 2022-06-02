# Ticket Vending Service

This a ticket vending service that aims to assign seats in a movie theatre according to 3 criterion: Satisfaction, Safety, and Availability. This was done as a technical assessment for a company that was wanting to see how I make unit tests.

## Assumptions

- "Satisfaction" is defined as (in this order) (according to [MentalFloss.com](https://www.mentalfloss.com/article/578639/best-place-to-sit-in-movie-theater)):
  - Prefer everyone in the request to be seated together.
    - **If this preference is not satisfied, everyone is seated as if they are individually booked.**
      - This is an unfortunate trade-off, however the codebase is perfectly capable of evolving to accommodate this. 
  - Prefer the last 2/3rd rows.
  - Prefer the center of rows, with respect to party size.
    - For example, for 20 seats, a party of 4 would prefer columns 9, 10, 11, and 12 if available.
      - Visual: `xxxxxxxxssssxxxxxxxx`, where `x` is a free seat and `s` is the seat taken from the above request.
  - Prefer as close to center as possible.
- "Safety" is defined as:
  - Must sit 3 seats apart from another person in same row.
  - If above cannot be satisfied, must sit 1 row apart from another person.
- "Availability" is defined as:
  - A booking has been completely processed for a respective seat.
  - There is no concept of "reserving" a seat without having purchased it.
- If the amount of seats available is less than the amount of seats requested, the ticket is blank and invalid. The
program will output the invalid request identifier. **Nobody from the request is given a seat.**
- A request identifier may only be processed once. If a request identifier is re-used, the program will output the 
invalid request. **The previous request will remain processed, but the new request will not be processed.**

## Setup

The following are required for this program:
- Java 8 or above
- Gradle

### Input

The following input file format is expected:
```
R### N
R### N
...
R### N
```
`###` - The request identifier

`N` - The number of people requesting seats for the specific request.

### Running

The following command runs the program:
```
gradle run --args="complete\path\to\input\file"
```

The following command runs all tests:
```
gradle test
```
