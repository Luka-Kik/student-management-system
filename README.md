- Database management layer in Src/Main/Java/se/iths/service/
- Entity class in Src/Main/Java/se/iths/entity/
- Rest controller in Src/Main/Java/se/iths/rest/
- custom exceptions in Src/Main/Java/se/iths/exceptions/
----------
- the list of present endpoints is found on the Payara server page on port 4848 (local server: http://localhost:4848/common/index.jsf)
- help method in the StudentRest class that checks if an email is already present in the database (thanks to Jessica Rodriguez for the idea)

_Update 19.4.2022._
- Added Subject and Teacher classes, REST methods and database services
- @ManyToMany between Student and Subject, @ManyToOne between Subject and Teacher
- HttpError message now sends a JSON
- Added Subject to Student method in Student REST
- Thanks to https://github.com/JesRod-1234/ for the inspiration

Update 6.5.2022.
- Fixed error messages, now in Json, with description and timestamp.