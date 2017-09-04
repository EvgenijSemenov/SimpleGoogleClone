# SimpleGoogleClone
Full page search for web pages

**Required tools:** *jdk 1.8+, MySQL 5.7+ and gradle 2.4+*.

**Short instruction:**
1. Clone this repo into new project folder.
2. Create *index_page* table in database:
```SQL
CREATE TABLE `index_page` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`url` VARCHAR(300) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`title` VARCHAR(300) NOT NULL COLLATE 'utf8mb4_unicode_ci',
	`text` LONGTEXT NOT NULL COLLATE 'utf8mb4_unicode_ci',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `UNIQUE_URL` (`url`),
	FULLTEXT INDEX `FULLTEXT` (`title`, `text`)
)
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1;
```
3. Rename *application.properties.simple* file to *application.properties* in *src\main\resources* folder.
4. Edit database configuration in *application.properties.simple* file.
5. Build project: *gradle build*.
6. Start server: *java -jar build/libs/SimpleGoogleClone-1.0-SNAPSHOT.jar*.
