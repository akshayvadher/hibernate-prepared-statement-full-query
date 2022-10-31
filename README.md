## The repo for reproducing hibernate issue

I want to see full hibernate query as asked in [StackOverflow Question](https://stackoverflow.com/questions/74139868)

## Changes
I am using `SQLExtractor.from`

`hibernate.criteria.literal_handling_mode=inline`

## Issue
It shows literal value for string but not other types like `ENUM` or `LocalDate` 


## How to check
Start the server using `gradlew run`

do `curl` to http://localhost:8080/ and see the console

```text
23:07:11.902 [default-nioEventLoopGroup-1-2] INFO  com.example.UserRepo - Query: select user0_.id as id1_1_, user0_.name as name2_1_ from user user0_ cross join cycle cycle1_ where user0_.name='a' and user0_.id=cycle1_.id and cycle1_.type=? and cycle1_.created=?
Hibernate: select user0_.id as id1_1_, user0_.name as name2_1_ from user user0_ cross join cycle cycle1_ where user0_.name='a' and user0_.id=cycle1_.id and cycle1_.type=? and cycle1_.created=? limit ?
23:07:11.903 [default-nioEventLoopGroup-1-2] TRACE o.h.type.descriptor.sql.BasicBinder - binding parameter [1] as [VARCHAR] - [A]
23:07:11.903 [default-nioEventLoopGroup-1-2] TRACE o.h.type.descriptor.sql.BasicBinder - binding parameter [2] as [DATE] - [2022-10-31]

```
