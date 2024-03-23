CREATE TABLE `Reservations` (
                        `id` INT(11) NOT NULL AUTO_INCREMENT,
                        `user_id` INT(11),
                        `room_id` INT(11),
                        `start_date` DATE,
                        `end_date` DATE,
                        `status` TINYINT(4),
                        PRIMARY KEY (`id`)
);
CREATE TABLE `Rooms` (
                         `id` INT(11) NOT NULL AUTO_INCREMENT,
                         `room_number` INT(11),
                         `quality_level` TINYINT(4),
                         `room_type` INT(11),
                         `floor` INT(11) DEFAULT '1',
                         `rate` DOUBLE(20),
                         `smoking_status` BOOLEAN(20) DEFAULT 'false',
                         `num_single_beds` INT(11) DEFAULT '0',
                         `num_double_beds` INT(11) DEFAULT '0',
                         `num_queen_beds` INT(11) DEFAULT '0',
                         PRIMARY KEY (`id`)
);
CREATE TABLE `Accounts` (
                            `id` INT(11) NOT NULL AUTO_INCREMENT,
                            `payment_id` INT(11),
                            `username` VARCHAR(255) DEFAULT '',
                            `password` VARCHAR(255) DEFAULT '',
                            `first_name` VARCHAR(255) DEFAULT '',
                            `last_name` VARCHAR(255) DEFAULT '',
                            `phone_number` VARCHAR(255) DEFAULT '',
                            `email` VARCHAR(255) DEFAULT '',
                            PRIMARY KEY (`id`)
);
CREATE TABLE `PaymentInfo` (
                               `id` INT(11) NOT NULL AUTO_INCREMENT,
                               `provider` VARCHAR(255) DEFAULT '',
                               `card_number` BIGINT,
                               `expiration_date` DATE(20),
                               `security_code` INT(11),
                               `zipcode` INT(11),
                               PRIMARY KEY (`id`)
);
CREATE TABLE `StoreItem` (
                             `id` INT(11) NOT NULL AUTO_INCREMENT,
                             `price` DOUBLE(20) DEFAULT '0',
                             `name` VARCHAR(255) DEFAULT '',
                             `description` TEXT(255) DEFAULT '',
                             `image` MEDIUMBLOB,
                             PRIMARY KEY (`id`)
);