DELETE FROM `user_option` WHERE `id` > 0;
DELETE FROM `language` WHERE `id` > 0;
DELETE FROM `token` WHERE `id` > 0;
DELETE FROM `attempt` WHERE `id` > 0;
DELETE FROM `season` WHERE `id` > 0;
DELETE FROM `exercise` WHERE `id` > 0;
DELETE FROM `user` WHERE `id` > 0;
TRUNCATE  TABLE `hibernate_sequence`;

INSERT INTO `language` (`id`, `image_url`, `name`) VALUES
(1, '../img/english.png', 'English'),
(2, '../img/slovak.png', 'Slovak');

INSERT INTO `user` (`id`, `created_at`, `email`, `password`, `verified`) VALUES
(3, '2021-08-29 08:40:47', 'testUser1@my1rm.com', '$argon2id$v=19$m=4096,t=3,p=1$nQ7X7iJOSnoqRUzw+Ssfqg$M6Atu1qu5jprdrWsCHIUEOFpUifeLK3TnByV0zQZON8', 1),
(4, '2021-08-29 08:40:47', 'testUser2@my1rm.com', '$argon2id$v=19$m=4096,t=3,p=1$ibFMIcMF/zCx0NEkUUQeYQ$svSxfKWXybE+nYR0xR0O3KIdi160ZPIEqZ77JGzrSOc', 0);

INSERT INTO `user_option` (`id`, `weight_unit`, `language_id`, `user_id`) VALUES
(5, 'KG', 1, 3),
(6, 'LBS', 1, 4);

INSERT INTO `token` (`id`, `created_at`, `token`, `type`, `user_id`) VALUES
(7, '2021-08-29 07:45:45', 'a45f2a98795ab676e520c25929836f04b810637a4797006d6995ba85315fa012', 'CONFIRM_EMAIL', 4);

INSERT INTO `season` (`id`, `color`, `created_at`, `name`, `user_id`) VALUES
(8, '#FF000', '2021-08-11 06:58:29', 'A B C', 3),
(9, '#00FF00', '2021-08-11 06:58:29', 'A B A B', 3),
(10, '#F2AC6F', '2021-08-11 06:58:29', 'PowerLifting P.', 4);

INSERT INTO `exercise` (`id`, `created_at`, `goal`, `name`, `user_id`) VALUES
(11, '2021-08-24 06:57:26', 150, 'Bench Press', 3),
(12, '2021-08-03 07:11:20', 250, 'Deadlift', 3),
(13, '2021-08-03 10:16:42', 200, 'Squat', 3),
(14, '2021-08-24 08:02:58', 120, 'Squat', 4),
(15, '2021-08-24 08:02:58', 90, 'Bench', 4),
(16, '2021-08-24 08:02:58', 150, 'Deadlift', 4);

INSERT INTO `attempt` (`id`, `repetitions`, `success`, `weight`, `exercise_id`, `season_id`, `user_id`, `date`) VALUES
(17, 1, 1, 110.0, 11, 8, 3, '2021-08-11 08:52:46'),
(18, 1, 1, 200.0, 12, 8, 3, '2021-08-28 08:52:51'),
(19, 1, 1, 160.0, 13, 8, 3, '2021-08-24 08:52:55'),
(20, 1, 1, 112.5, 11, 9, 3, '2021-08-11 08:52:46'),
(21, 1, 1, 205.0, 12, 9, 3, '2021-08-28 08:52:51'),
(22, 1, 0, 165.0, 13, 9, 3, '2021-08-24 08:52:55'),
(23, 1, 1, 110.0, 14, 10, 4, '2021-08-11 08:52:46'),
(24, 1, 1, 80.0, 15, 10, 4, '2021-08-28 08:52:51'),
(25, 1, 1, 135.0, 16, 10, 4, '2021-08-24 08:52:55');

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(25);