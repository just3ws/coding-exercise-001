# Build a REST API to access your system

Tests for this section are required as well.

Within the same code base, build a standalone REST API with the following
endpoints:

- **POST /records**: `POST` a single data line in any of the 3 formats supported
  by your existing code
- **GET /records/gender**: Returns records sorted by `gender`.
- **GET /records/birthdate**: Returns records sorted by `birth date`
- **GET /records/name**: Returns records sorted by `name`

It's your choice how you render the output from these endpoints as long as it
well structured data.

These endpoints should return `JSON`.

To keep it simple, don't worry about using a persistent datastore.
