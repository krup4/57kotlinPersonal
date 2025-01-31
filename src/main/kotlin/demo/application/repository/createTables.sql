CREATE TABLE IF NOT EXISTS users (
    id SERIAl PRIMARY KEY,
    uuid UUID NOT NULL, --уникальный идентификатор пользователя
    login TEXT NOT NULL, --логин от аккаунта
    password TEXT NOT NULL, --пароль от аккаунта
    gender ENUM('male', 'female'), --пол
    age INT, --возраст
    lastName TEXT, --фамилия
    firstName TEXT, --имя
    photoUrl TEXT --путь до файла с фотографией пользователя
);

CREATE TABLE IF NOT EXISTS reactions (
    id SERIAl PRIMARY KEY,
    userFrom UUID NOT NULL, --идентификатор пользователя, который поставил реакцию
    userTo UUID NOT NULL, --идентификатор пользователя, которому поставили реакцию
    type BOOLEAN NOT NULL, --тип реакции(0 - дизлайк, 1 - лайк)

    --связь полей пользователей с таблицой users
    FOREIGN KEY (userFrom) REFERENCES users(uuid), 
    FOREIGN KEY (userTo) REFERENCES users(uuid)
);