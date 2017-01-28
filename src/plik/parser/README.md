# Build a system to parse and sort a set of records

Create a command line app that takes as input a file with a set of records in
one of three formats described below and outputs (to the screen) the set of
records sorted in one of three ways.

## Input

- A record consists of the following **5 fields**:
  - `last name`
  - `first name`
  - `gender`
  - `date of birth`
  - `favorite color`
- The input is **3 files** each containing records stored in a different format.
- You may generate these files yourself, and you can make certain assumptions
  if it makes solving your problem easier.
- The **pipe-delimited** file lists each record as follows:

  ```text
  LastName | FirstName | Gender | FavoriteColor | DateOfBirth
  ```

- The **comma-delimited** file looks like this:  

  ```text
  LastName, FirstName, Gender, FavoriteColor, DateOfBirth
  ```

- The **space-delimited** file looks like this:  

  ```text
  LastName FirstName Gender FavoriteColor DateOfBirth
  ```

You may assume that the delimiters (commas, pipes and spaces) do not appear
anywhere in the data values themselves.

Write a program in a language of your choice to read in records from these
files and combine them into a single set of records.

## Output

Create and display **3 views** of the data you read in:

- **Output 1**: Sorted by `gender` (`females` before `males`) then by `last
  name ascending`.
- **Output 2**: Sorted by `birth date ascending`.
- **Output 3**: Sorted by `last name descending`.

Display dates in the format `M/D/YYYY`.
