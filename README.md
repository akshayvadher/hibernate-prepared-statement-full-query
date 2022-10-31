## The repo for reproducing hibernate issue

I want to see full hibernate query as asked in [StackOverflow Question](https://stackoverflow.com/questions/74139868)

## Changes
I am using `SQLExtractor.from`

`hibernate.criteria.literal_handling_mode=inline`

## Issue
It shows literal value for string but not other types like `ENUM` or `LocalDate` 
