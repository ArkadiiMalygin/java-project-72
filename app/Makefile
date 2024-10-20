.PHONY: build

build:
	./gradlew clean
	./gradlew checkstyleMain
	./gradlew checkstyleTest
	./gradlew test
	./gradlew build

report:
	./gradlew jacocoTestReport
