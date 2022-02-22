# Arham Siddiqui's Walmart Assessment (Backend)

This is my attempt at the Walmart Assessment (Backend).

## Assumptions

- "Satisfaction" is defined as (in this order) (according to [MentalFloss.com](https://www.mentalfloss.com/article/578639/best-place-to-sit-in-movie-theater)):
  - Prefer everyone in the request to be seated together.
    - **If this preference is not satisfied, everyone is seated as if they are individually booked.**
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
