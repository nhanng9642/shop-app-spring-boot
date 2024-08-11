-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `published_year` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `quantity_available` int DEFAULT NULL,
  `description` text,
  `book_image` varchar(255) DEFAULT NULL,
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Book UPDATED',1,'Author UPDATED','Crown Publishing Group',2018,100,12,'Autobiography of the former First Lady.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024503/becoming_heudtu.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(2,'Educated: A Memoir',2,'Tara Westover','Random House',2018,24.5,0,'Memoir about a woman who leaves her survivalist family.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024553/educated_jiwuo5.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(3,'The Girl on the Train',3,'Paula Hawkins','Riverhead Books',2015,19.75,70,'Thriller novel with a gripping narrative.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024614/girl_on_train_rkgyra.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(4,'The Great Gatsby',4,'F. Scott Fitzgerald','Scribner',1925,25.99,50,'Classic fiction novel about the American Dream.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024606/great_gatsby_iqga1t.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(5,'Harry Potter and the Sorcerer\'s Stone',5,'J.K. Rowling','Bloomsbury Publishing',1997,30,80,'Fantasy novel introducing the wizarding world.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024619/harry_potter_yqw8f0.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(6,'The Power of Now',6,'Eckhart Tolle','New World Library',1997,15.99,55,'Self-help book on spiritual growth and mindfulness.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024163/power_of_now_oe8iqk.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(7,'Sapiens: A Brief History of Humankind',7,'Yuval Noah Harari','Harper',2014,21.5,65,'Historical exploration of the human species.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024507/sapiens_wtxirg.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(8,'Thinking, Fast and Slow',8,'Daniel Kahneman','Farrar, Straus and Giroux',2011,22.75,40,'Psychology book about decision-making processes.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024596/thinking_fast_slow_zlz12x.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(9,'The Alchemist',5,'Paulo Coelho','HarperOne',1988,20.99,75,'Fiction novel about a young shepherd on a journey.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024523/alchemist_cpcxxd.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(11,'Born a Crime',1,'Trevor Noah','Spiegel & Grau',2016,23.5,40,'Memoir about Trevor Noah\'s childhood in South Africa.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024557/born_a_crime_bqhfka.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(12,'The Da Vinci Code',3,'Dan Brown','Doubleday',2003,18.75,65,'Thriller involving hidden secrets and codes.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024558/da_vinci_code_vj7kvm.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(13,'Animal Farm',4,'George Orwell','Secker & Warburg',1945,16.99,70,'Allegorical novella about the Russian Revolution.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024504/animal_farm_qdqnqb.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(14,'The Hobbit',5,'J.R.R. Tolkien','Allen & Unwin',1937,19.99,75,'Fantasy adventure novel set in Middle-earth.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024609/hobbit_vvqh0n.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(15,'The Subtle Art of Not Giving a F*ck',6,'Mark Manson','HarperOne',2016,21.25,50,'Self-help book about focusing on what truly matters.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024568/subtle_art_erlzey.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(16,'Guns, Germs, and Steel',7,'Jared Diamond','W.W. Norton & Company',1997,24.5,60,'Historical analysis of human societies and development.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024621/guns_germs_steel_bkwfhn.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(17,'Man\'s Search for Meaning',8,'Viktor E. Frankl','Beacon Press',1946,20.75,45,'Psychology book detailing Frankl\'s experiences in Auschwitz.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024539/mans_search_meaning_ibwxva.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(18,'The Catcher in the Rye',4,'J.D. Salinger','Little, Brown and Company',1951,18.99,4,'Classic novel following Holden Caulfield\'s experiences.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024510/catcher_in_rye_erxfkh.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(19,'The Secret',6,'Rhonda Byrne','Atria Books',2006,19.25,55,'Self-help book discussing the law of attraction.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024486/the_secret_h4qvaa.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(20,'The Brothers Karamazov',4,'Fyodor Dostoevsky','The Russian Messenger',1880,26.99,30,'A complex novel weaving themes of morality, spirituality, and family dynamics, following the lives of the Karamazov brothers in 19th-century Russia.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024495/brothers_karamazov_xt4ses.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(21,'Bossypants',1,'Tina Fey','Reagan Arthur Books',2011,18.5,40,'A comedic memoir by Tina Fey, reflecting on her life, career, and experiences in the entertainment industry.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024554/bossypants_phsxre.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(22,'Gone Girl',3,'Gillian Flynn','Crown Publishing Group',2012,20.25,55,'A psychological thriller revolving around the mysterious disappearance of Amy Dunne and the ensuing investigation that uncovers hidden truths about marriage and relationships.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024622/gone_girl_wawcu3.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(23,'The Picture of Dorian Gray',4,'Oscar Wilde','Lippincott\'s Monthly Magazine',1890,22.75,25,'A philosophical novel exploring the consequences of vanity and the pursuit of eternal youth, as depicted through the life of Dorian Gray.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024590/dorian_gray_jlnlgx.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(24,'The Name of the Wind',5,'Patrick Rothfuss','DAW Books',2007,24.99,35,'An epic fantasy following the life of Kvothe, a magically gifted young man, as he recounts his journey to becoming a legendary figure.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024531/name_of_wind_mawagh.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(25,'You Are a Badass',6,'Jen Sincero','Running Press',2013,19.99,45,'A motivational self-help book encouraging readers to embrace their inner potential and live life fearlessly.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024515/you_are_badass_uvo3fb.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(26,'1491: New Revelations of the Americas Before Columbus',7,'Charles C. Mann','Knopf',2005,23.5,50,'A historical exploration challenging conventional notions about the Americas before the arrival of Christopher Columbus, shedding light on the complexity and sophistication of indigenous societies.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024517/1491_zmfqmh.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(27,'Quiet: The Power of Introverts in a World That Can\'t Stop Talking',8,'Susan Cain','Crown Publishing Group',2012,21.5,60,'A psychological examination of introversion, highlighting its strengths and advantages in a society that often values extroversion.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024533/quiet_ksf7id.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(28,'The Road',4,'Cormac McCarthy','Alfred A. Knopf',2006,20.99,30,'A post-apocalyptic novel following the journey of a father and son through a desolate landscape, exploring themes of survival, hope, and humanity.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024612/the_road_stcb9r.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(29,'The 7 Habits of Highly Effective People',6,'Stephen R. Covey','Free Press',1989,25.25,55,'A self-help book presenting a holistic approach to personal and professional effectiveness based on seven habits that foster growth and success.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024550/7_habits_detw2t.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(30,'The Martian',4,'Andy Weir','Crown Publishing Group',2014,18.99,60,'Science fiction novel following an astronaut stranded on Mars and his struggle for survival.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024548/the_martian_mpgsk0.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(31,'Me Talk Pretty One Day',1,'David Sedaris','Little, Brown and Company',2000,17.5,3,'Humorous autobiographical essays reflecting on the Author\'s experiences and observations.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024492/me_talk_pretty_mpeohb.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(32,'The Girl with the Dragon Tattoo',3,'Stieg Larsson','Norstedts Förlag',2005,19.75,55,'Mystery thriller featuring journalist Mikael Blomkvist and hacker Lisbeth Salander investigating a disappearance.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024616/girl_dragon_tattoo_srt0fm.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(33,'Brave New World',4,'Aldous Huxley','Chatto & Windus',1932,20.25,50,'Dystopian novel exploring a future society controlled by technology, genetic engineering, and conditioning.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024574/brave_new_world_ckccpc.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(34,'The Name of the Rose',4,'Umberto Eco','Bompiani',1980,21.99,40,'Historical mystery set in an Italian monastery during the Middle Ages, involving murder and theological debate.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024578/name_of_rose_xnktzb.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(35,'The Goldfinch',4,'Donna Tartt','Little, Brown and Company',2013,22.5,65,'Coming-of-age novel centered around a young boy who steals a famous painting after a tragic event.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024566/goldfinch_va0ulp.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(36,'The Night Circus',5,'Erin Morgenstern','Doubleday',2011,23.75,70,'Fantasy novel featuring a magical competition between two young illusionists.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024526/night_circus_oakkr5.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(37,'The Power',4,'Naomi Alderman','Viking',2016,19.99,60,'Science fiction novel exploring a world where women develop the ability to generate electrical power.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024576/the_power_wqcv5s.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(38,'The Immortal Life of Henrietta Lacks',1,'Rebecca Skloot','Crown Publishing Group',2010,20.25,50,'Non-fiction book chronicling the story of Henrietta Lacks and the immortal cell line known as HeLa.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024603/immortal_life_nkh7uv.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(39,'The Hitchhiker\'s Guide to the Galaxy',4,'Douglas Adams','Pan Books',1979,18.5,60,'A comedic science fiction series following the misadventures of Arthur Dent.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024571/hitchhikers_guide_hvdqxe.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(40,'Pride and Prejudice',4,'Jane Austen','T. Egerton, Whitehall',1813,22.99,30,'A classic romance novel.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024601/pride_and_prejudice_sqnmht.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(41,'The Lord of the Rings',5,'J.R.R. Tolkien','George Allen & Unwin',1954,28,80,'Epic fantasy novel following the quest to destroy the One Ring.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024579/lord_of_rings_b5sysr.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(42,'Crime and Punishment',4,'Fyodor Dostoevsky','The Russian Messenger',1866,19.25,2,'A psychological thriller following the moral dilemmas of Raskolnikov.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024513/crime_and_punishment_cooltg.jpg','2024-07-01 11:24:11','2024-07-01 11:24:11'),(43,'The Autobiography of Malcolm X',1,'Malcolm X','Ballantine Books',1965,25.99,10,'An autobiography of Malcolm X, an American Muslim minister and human rights activist.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024529/malcolm_x_autobiography_iewmsm.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(44,'Memoirs of a Geisha',2,'Arthur Golden','Knopf Doubleday Publishing Group',1997,16.99,5,'A memoir of a Japanese geisha, Chiyo Sakura.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024485/memoirs_of_a_geisha_bgxvhb.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(45,'American Gods',5,'Neil Gaiman','William Morrow & Company',2001,17.99,9,'A fantasy novel about a shadow war brewing between Old World gods and New World gods.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024594/american_gods_sov7w3.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(46,'A Short History of Nearly Everything',7,'Bill Bryson','Broadway Books',2003,14.99,4,'A history book that covers a wide range of topics, from the Big Bang to the human body.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024611/a_short_history_of_nearly_everything_xa5moy.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(47,'The Road Less Traveled',6,'M. Scott Peck','Simon & Schuster',1978,20.5,60,'A self-help book exploring personal growth and spiritual development.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024597/road_less_traveled_onxylh.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(48,'The Shining',4,'Stephen King','Doubleday',1977,19.99,55,'A horror novel about a haunted hotel and a writer’s descent into madness.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024547/the_shining_lbroxc.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(49,'Anna Karenina',4,'Leo Tolstoy','The Russian Messenger',1878,23.5,50,'A classic novel depicting tragic love and societal norms in 19th-century Russia.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024593/anna_karenina_uexuow.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(50,'The Grapes of Wrath',4,'John Steinbeck','The Viking Press',1939,21.25,45,'A novel portraying the hardships faced by migrant farmers during the Great Depression.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024563/grapes_of_wrath_tjetch.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(51,'The Stranger',4,'Albert Camus','Gallimard',1942,18.99,40,'A philosophical novel exploring themes of existentialism and absurdity.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024542/the_stranger_opi4mg.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(52,'A Brief History of Time',7,'Stephen Hawking','Bantam Books',1988,25.99,65,'A popular science book discussing cosmology, black holes, and the universe.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024570/brief_history_time_rmhp8l.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(53,'The Girl with the Pearl Earring',4,'Tracy Chevalier','HarperCollins',1999,17.99,70,'Historical novel inspired by Vermeer’s painting of the same name.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024502/girl_pearl_earring_blb6eo.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(54,'The Hunger Games',5,'Suzanne Collins','Scholastic Corporation',2008,22.75,75,'A dystopian novel following a young woman’s fight for survival in a televised competition.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024544/hunger_games_rwoiur.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(55,'The Joy Luck Club',4,'Amy Tan','G.P. Putnam’s Sons',1989,20.99,60,'Novel exploring the relationships between Chinese-American immigrant families.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024560/joy_luck_club_wtf151.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(56,'Siddhartha',1,'Hermann Hesse','S. Fischer Verlag',1922,18.5,55,'A novel exploring the spiritual journey of a man named Siddhartha.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024489/siddhartha_hauooj.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(57,'1984',4,'George Orwell','Secker & Warburg',1949,19.99,60,'A dystopian novel portraying a totalitarian society and its manipulation of truth.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024583/1984_otyyrj.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(58,'To Kill a Mockingbird',4,'Harper Lee','J. B. Lippincott & Co.',1960,21.5,50,'A classic novel addressing racial injustice and moral growth.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024494/to_kill_mockingbird_lvvjrl.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(59,'The Book of Five Rings',7,'Miyamoto Musashi','Various',1645,16.75,55,'A Japanese text on martial arts strategy and philosophy.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024541/book_five_rings_e7wnbn.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(60,'The Count of Monte Cristo',4,'Alexandre Dumas','Ponson du Terrail',1844,23.99,40,'Adventure novel following a man seeking vengeance and redemption.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024528/count_monte_cristo_gth1wr.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(61,'One Hundred Years of Solitude',4,'Gabriel García Márquez','Editorial Sudamericana',1967,25.25,45,'Magical realism novel chronicling the Buendía family over generations.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024520/one_hundred_years_hltvr4.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(62,'The Wind-Up Bird Chronicle',4,'Haruki Murakami','Shinchosha',1994,22.99,55,'Surreal novel exploring themes of loss, identity, and human connection.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024607/wind_up_bird_chronicle_lcuesa.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(63,'The Stand',4,'Stephen King','Doubleday',1978,20.75,65,'Post-apocalyptic novel depicting a world devastated by a pandemic.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024581/the_stand_brrqis.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(64,'The Sun Also Rises',4,'Ernest Hemingway','Scribner',1926,18.5,60,'A novel capturing the disillusionment of the Lost Generation after World War I.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024545/sun_also_rises_zvdg7w.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(65,'The Art of Happiness',8,'Dalai Lama XIV, Howard Cutler','Riverhead Books',1998,17.99,70,'A book exploring the principles of happiness and well-being.','https://res.cloudinary.com/dn1mgczd2/image/upload/v1720024604/The_Art_of_Happiness_ddrrm5.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(66,'The Secret Life of Bees',4,'Sue Monk Kidd','Viking Press',2002,19.25,65,'Novel about a young girl in South Carolina finding solace in beekeeping.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024551/secret_life_bees_ddcrir.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(67,'Moby-Dick',4,'Herman Melville','Richard Bentley',1851,24.99,40,'Novel exploring themes of obsession and revenge through the story of Captain Ahab and the white whale.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024535/moby_dick_ox3wpq.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(68,'The Little Prince',4,'Antoine de Saint-Exupéry','Reynal & Hitchcock',1943,19.75,60,'Fable about a young prince exploring the complexities of human nature.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024588/little_prince_ojtt0h.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(69,'Frankenstein',4,'Mary Shelley','Lackington, Hughes, Harding, Mavor, & Jones',1818,18.5,50,'Gothic novel exploring the consequences of scientific ambition and creation.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024573/frankenstein_xkdggi.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(70,'Wuthering Heights',4,'Emily Brontë','Thomas Cautley Newby',1847,20.25,45,'A tale of love, revenge, and the destructive power of nature.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024497/wuthering_heights_tesnky.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(71,'The Adventures of Sherlock Holmes',4,'Arthur Conan Doyle','George Newnes Ltd',1892,17.99,60,'Collection of detective stories featuring Sherlock Holmes.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024561/adventures_sherlock_holmes_ooxal6.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(72,'Dracula',3,'Bram Stoker','Archibald Constable and Company',1897,18.99,40,'Classic Gothic horror novel about Count Dracula\'s attempt to move from Transylvania to England.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024524/dracula_weq3ut.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(73,'The Exorcist',3,'William Peter Blatty','Harper & Row',1971,20.5,55,'A novel about the demonic possession of a young girl and the attempts to exorcise the demon.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024536/exorcist_dznc03.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(74,'The Haunting of Hill House',3,'Shirley Jackson','Viking Press',1959,22.99,50,'A novel exploring the supernatural events that occur at Hill House.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024586/haunting_hill_house_cgzjle.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(75,'Pet Sematary',3,'Stephen King','Doubleday',1983,19.75,60,'Horror novel about a cemetery that brings the dead back to life.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024491/pet_sematary_ic70vm.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(76,'American Psycho',3,'Bret Easton Ellis','Vintage Books',1991,23.5,55,'A psychological horror novel following an investment banker who is also a serial killer.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024565/american_psycho_xrtbau.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(77,'House of Leaves',3,'Mark Z. Danielewski','Pantheon Books',2000,24.99,40,'A complex horror novel with unconventional formatting, exploring a house that defies the laws of physics.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024591/house_of_leaves_lfhboi.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(78,'The Girl Next Door',3,'Jack Ketchum','Macabre Ink',1989,20.25,50,'A horror novel based on a true story of abuse and torment in suburban America.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024518/girl_next_door_ozh1vb.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(79,'Hell House',3,'Richard Matheson','Viking Press',1971,18.99,65,'A haunted house novel where a physicist investigates supernatural occurrences.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024512/hell_house_imweid.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(80,'The Call of Cthulhu and Other Weird Stories',3,'H.P. Lovecraft','Arkham House',1928,17.5,60,'A collection of Lovecraftian horror stories featuring cosmic entities and ancient horrors.','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720024599/call_of_cthulhu_i8srcu.jpg','2024-07-01 11:24:12','2024-07-01 11:24:12'),(81,'Book NEW',1,'Author NEW','Publisher NEW',2021,100,NULL,NULL,NULL,'2024-07-01 15:56:10','2024-07-01 15:56:10'),(82,'Book NEW',1,'Author NEW','Publisher NEW',2021,100,NULL,NULL,NULL,'2024-07-01 16:19:10','2024-07-01 16:19:10'),(84,'Title',2,'Author','NXB Tre',2000,20,10,'Description','ddd','2024-07-06 03:04:32','2024-07-06 03:04:32'),(85,'Title',2,'Author','NXB Tre',2000,20,10,'Description','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720235090/file_fqk2ys.jpg','2024-07-06 03:04:50','2024-07-06 03:04:50'),(86,'Title',2,'Author','NXB Tre',2000,20,10,'Description','http://res.cloudinary.com/dn1mgczd2/image/upload/v1720253348/file_qyu7hb.jpg','2024-07-06 08:09:08','2024-07-06 08:09:08');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) DEFAULT NULL,
  `description` text,
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Autobiography','Life stories written by the person themselves.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(2,'Memoir','Personal narratives focusing on significant events and memories.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(3,'Thriller','Suspenseful stories often involving mysteries, crimes, or dark plots.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(4,'Fiction','Imaginary worlds, characters, and events that captivate readers.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(5,'Fantasy','Magical realms, mythical creatures, and boundless adventures.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(6,'History','Accounts of the past, encompassing historical events, figures, and cultures.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(7,'Self-help','Guides providing knowledge and methods for self-improvement.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(8,'Psychology','Studies exploring human behavior, thought processes, and mental states.','2024-07-01 11:24:11','2024-07-01 11:24:11'),(9,'NEW CATEGORY','NEW DESCRIPTION','2024-07-06 10:44:28','2024-07-06 10:44:28'),(10,'NEW CATEGORY','NEW DESCRIPTION','2024-07-06 10:45:47','2024-07-06 10:45:47'),(11,'NEW CATEGORY','NEW DESCRIPTION','2024-07-06 10:45:51','2024-07-06 10:45:51'),(12,'CATEGORY UPDATED','DESCRIPTION UPDATED','2024-07-06 10:46:28','2024-07-06 10:46:28'),(13,'CATEGORY UPDATED','DESCRIPTION UPDATED','2024-07-06 10:46:44','2024-07-06 10:46:44');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint unsigned NOT NULL,
  `book_id` bigint unsigned NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `order_id` (`order_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `sale_order` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (21,1,1,2,20,40,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(22,1,2,1,60,60,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(23,2,1,3,20,60,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(24,2,3,2,45.25,90.5,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(25,3,4,1,200.75,200.75,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(26,3,5,2,30,60,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(27,4,1,1,20,20,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(28,4,6,4,50,200,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(29,5,2,3,60,180,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(30,5,3,1,45.25,45.25,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(31,5,7,5,25,125,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(32,2,4,1,200.75,200.75,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(33,3,1,1,20,20,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(34,4,2,2,60,120,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(35,1,5,3,30,90,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(36,2,6,1,50,50,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(37,3,7,2,25,50,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(38,4,8,4,40,160,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(39,5,9,1,80,80,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(40,5,2,2,35,70,'2024-07-07 02:59:11','2024-07-07 02:59:11'),(41,1,1,10,10,10,'2024-07-09 15:20:37','2024-07-09 15:20:37'),(42,36,1,2,100,200,'2024-07-09 15:25:49','2024-07-09 15:25:49'),(43,36,2,1,25.9,25.9,'2024-07-09 15:25:49','2024-07-09 15:25:49'),(44,37,1,2,100,200,'2024-07-09 15:26:44','2024-07-09 15:26:44'),(45,37,2,1,25.9,25.9,'2024-07-09 15:26:44','2024-07-09 15:26:44'),(46,38,1,2,100,200,'2024-07-10 04:20:18','2024-07-10 04:20:18'),(47,38,2,1,25.9,25.9,'2024-07-10 04:20:18','2024-07-10 04:20:18'),(48,39,1,2,100,200,'2024-07-10 04:23:06','2024-07-10 04:23:06'),(49,39,2,1,25.9,25.9,'2024-07-10 04:23:06','2024-07-10 04:23:06'),(50,41,1,2,100,200,'2024-07-10 04:24:04','2024-07-10 04:24:04'),(51,41,2,1,25.9,25.9,'2024-07-10 04:24:04','2024-07-10 04:24:04'),(52,42,1,2,100,200,'2024-07-10 04:28:12','2024-07-10 04:28:12'),(53,42,2,1,25.9,25.9,'2024-07-10 04:28:12','2024-07-10 04:28:12'),(54,43,1,2,100,200,'2024-07-10 04:28:45','2024-07-10 04:28:45'),(55,43,2,1,25.9,25.9,'2024-07-10 04:28:45','2024-07-10 04:28:45');
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_order`
--

DROP TABLE IF EXISTS `sale_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `order_date` date DEFAULT NULL,
  `total` float DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sale_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_order`
--

LOCK TABLES `sale_order` WRITE;
/*!40000 ALTER TABLE `sale_order` DISABLE KEYS */;
INSERT INTO `sale_order` VALUES (1,1,'2024-07-01',100,'123 Main St','Pending','2024-07-07 02:58:12','2024-07-07 02:58:12'),(2,2,'2024-07-02',150.5,'456 Oak St','SHIPPED','2024-07-07 02:58:12','2024-07-07 02:58:12'),(3,3,'2024-07-03',200.75,'789 Pine St','Delivered','2024-07-07 02:58:12','2024-07-07 02:58:12'),(4,4,'2024-07-04',250.25,'321 Maple St','Cancelled','2024-07-07 02:58:12','2024-07-07 02:58:12'),(5,5,'2024-07-05',300,'654 Cedar St','Processing','2024-07-07 02:58:12','2024-07-07 02:58:12'),(12,5,'2024-07-09',225.9,'1234 Main St','PENDING','2024-07-09 07:03:22','2024-07-09 07:03:22'),(13,5,'2024-07-09',225.9,'1234 Main St','PENDING','2024-07-09 07:04:35','2024-07-09 07:04:35'),(36,5,'2024-07-09',225.9,'1234 Main St','PENDING','2024-07-09 15:25:49','2024-07-09 15:25:49'),(37,5,'2024-07-09',225.9,'1234 Main St','PENDING','2024-07-09 15:26:44','2024-07-09 15:26:44'),(38,5,'2024-07-10',225.9,'1234 Main St','PENDING','2024-07-10 04:20:18','2024-07-10 04:20:18'),(39,5,'2024-07-10',225.9,'1234 Main St','PENDING','2024-07-10 04:23:06','2024-07-10 04:23:06'),(41,5,'2024-07-10',225.9,'1234 Main St','PENDING','2024-07-10 04:24:04','2024-07-10 04:24:04'),(42,5,'2024-07-10',225.9,'1234 Main St','PENDING','2024-07-10 04:28:12','2024-07-10 04:28:12'),(43,5,'2024-07-10',225.9,'1234 Main St','PENDING','2024-07-10 04:28:44','2024-07-10 04:28:44'),(46,5,'2024-07-10',0,'1234 Main St','PENDING','2024-07-10 04:29:40','2024-07-10 04:29:40'),(47,5,'2024-07-10',0,'1234 Main St','PENDING','2024-07-10 04:32:25','2024-07-10 04:32:25'),(48,5,'2024-07-10',0,'1234 Main St','PENDING','2024-07-10 04:32:35','2024-07-10 04:32:35');
/*!40000 ALTER TABLE `sale_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `user_id` bigint unsigned NOT NULL,
  `revoked` tinyint(1) NOT NULL DEFAULT '0',
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_token_user` (`user_id`),
  CONSTRAINT `token_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGduMiIsImlhdCI6MTcxOTgzMjE1MSwiZXhwIjoxNzIwNDM2OTUxfQ.fLryaKKIzVtCnilFEB-ctg8-lo8JhHoNGLyVwTiwnsGi2Ew0aa3GqGCLIYtaKsx4N7Znw9sECPyuVYpNC6MXBA',1,0,'2024-07-01 11:09:11','2024-07-01 11:09:11'),(2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MTk4NDk2MzEsImV4cCI6MTcyMDQ1NDQzMX0.gnabPDzxRigtd5jbcEoFjLNELCiuvNT3ZRPtDooZ2pCtBI9PyDjwdVJD7hgsiYZ3EUDfYaTVre5XCdOmMhmXXQ',2,1,'2024-07-01 16:00:31','2024-07-01 16:00:31'),(3,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEyIiwiaWF0IjoxNzIwMzIwNzgyLCJleHAiOjE3MjA5MjU1ODJ9.Lu5iijZ9YCUx6Fk_qBlz6FuWD05vrHr6OPCXs0PiM0PW8OJxSHQ6nuzlAKyxUsZ59bgwAyEpTn21rSoLvGVL7w',3,1,'2024-07-07 02:53:02','2024-07-07 02:53:02'),(4,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEzIiwiaWF0IjoxNzIwMzIwNzkzLCJleHAiOjE3MjA5MjU1OTN9.pBEjWObCmM4U1GTaMhurQSs3cQvUt4sRKQfHJRrbHa_XuB85mukaxl2ZGTey-045JKMYcE4HuQQ_rYvB00ylXg',4,0,'2024-07-07 02:53:13','2024-07-07 02:53:13'),(5,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGE0IiwiaWF0IjoxNzIwMzIwODAxLCJleHAiOjE3MjA5MjU2MDF9.sdlyIBcGncfOLfng1pQ8nxbD9RvGsffJAvQoyt3UfHfLGa8kyX8SAaiKeUlswGR_cEazIOcOClf2TEnV__VsJA',5,0,'2024-07-07 02:53:21','2024-07-07 02:53:21'),(6,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjA1MDU4MTYsImV4cCI6MTcyMTExMDYxNn0.ErGMRfXbPICfaMz3mV2feqKjOPAEDHZMacSER2IzLOXfNwo_aXxK1lNjVt4eAPi2p8A8QtvuZyVRTEkBYygUYw',2,1,'2024-07-09 06:16:56','2024-07-09 06:16:56'),(7,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjA1MDU5MTksImV4cCI6MTcyMTExMDcxOX0.keynfW-Gz3qsGjwcgnIFJ07ShcA5Y8y31d9BjybHJAU_ZjWg9a4cmIL3x_eEn6F-q5-Y8P1ax7KveoQRi_vD3Q',2,1,'2024-07-09 06:18:39','2024-07-09 06:18:39'),(8,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEyIiwiaWF0IjoxNzIzMDg2MjMwLCJleHAiOjE3MjM2OTEwMzB9.Y4i9pnnz3ognrVg7EThk8y-YzC19cH7g0PlcmReWyVVhvAQAqh0fxcRwoi9hUmXV2TP17Sviz43rK1-6sqIMAg',3,1,'2024-08-08 03:03:50','2024-08-08 03:03:50'),(9,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEyIiwiaWF0IjoxNzIzMTkyOTcxLCJleHAiOjE3MjM3OTc3NzF9.M5UHszbkglMEuj56VHP8uax57b_7gZ3jlEVICTDZWxKeadjycKfJzTWucApo24uEIUd4xVeKb7t72Sg2Vgj0ZA',3,1,'2024-08-09 08:42:51','2024-08-09 08:42:51'),(10,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEyIiwiaWF0IjoxNzIzMTkyOTgyLCJleHAiOjE3MjM3OTc3ODJ9.9HP3CnI2-54_bURdooLO1txqb0ALef_Z08sR8Aazr1NPy6pzgf_RSvYcp2ssHZVKXbUolKNRvBnEL0LCeajbrA',3,1,'2024-08-09 08:43:02','2024-08-09 08:43:02'),(11,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEyIiwiaWF0IjoxNzIzMjA2OTAwLCJleHAiOjE3MjM4MTE3MDB9.-DXgyyXrXDf3cqgqS-FhZAE1Ecuh3crg0EbjiKUrCRGbWoN-OyOsCoE_NOBR1fixiMEKW--xu8D0bHgmfdpjlA',3,1,'2024-08-09 12:35:00','2024-08-09 12:35:00'),(12,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDcxMDUsImV4cCI6MTcyMzgxMTkwNX0.5eACmCEKrBHRr0e1hioAVS0-E9YvNl6w2f23xFxupSgPmG_IpNAzPHToOW9qYyQIxSD-uID_KoPL6915_9AtBA',2,1,'2024-08-09 12:38:25','2024-08-09 12:38:25'),(13,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDczMTIsImV4cCI6MTcyMzgxMjExMn0.pBsr4BVvS_fTBwWkb9Oz0XHp7XjKjR43KYVe2Qfgyd3YeBqLhM-8zcV_juRnMoYWQhmsDNPbXhV2DCzakskHIw',2,1,'2024-08-09 12:41:52','2024-08-09 12:41:52'),(14,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDczNjIsImV4cCI6MTcyMzgxMjE2Mn0.eYDvLcYjTN3dFAGqSi2-igwdjKFfFGFgCHr_q207pSftSJmk9RLcwbQ3EOxuh6BeU_EaPKGA2x5WjqMb6RkTbQ',2,1,'2024-08-09 12:42:42','2024-08-09 12:42:42'),(15,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDc0NDEsImV4cCI6MTcyMzgxMjI0MX0.k5Gk4Dj_rxmQdDv4hYwaOsu_jB1DRN-2JzGGV-KtjngO3SjM6ArzWhKIjMw4OQAdtVNz_i3I7_EJnoj96y9OLg',2,1,'2024-08-09 12:44:02','2024-08-09 12:44:02'),(16,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDc2MjUsImV4cCI6MTcyMzgxMjQyNX0.yVcFsWE1po5NgvmryS68Z6_sxM3YH9Kgy61hzf5WwHadP7ZoXwT0YJjf5l3RjNishV_E8u6cYIWSb0nB3Dwt8Q',2,1,'2024-08-09 12:47:05','2024-08-09 12:47:05'),(17,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDc2OTEsImV4cCI6MTcyMzgxMjQ5MX0.xBDSTpDcwucE6zrBexendQWoSYog8yr0kLZ_0YUH2iG0kfqVwj667aqoD4n3MeLvXKJIJtfciWFW_5SUyKgiBQ',2,1,'2024-08-09 12:48:11','2024-08-09 12:48:11'),(18,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDc3MzMsImV4cCI6MTcyMzgxMjUzM30.bunA1WlA6-frADX5zV9FkHrvWP0cW5nq71atbd6hEIxxflc6H8pd3CnSTOFhkJu2lB5pN-ya63TDJO20xjqsXg',2,1,'2024-08-09 12:48:53','2024-08-09 12:48:53'),(19,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDc3NTUsImV4cCI6MTcyMzgxMjU1NX0.1e9BXBJ7OQseEXNgmqCC8SfgFqJrFYeGdgBHAeSlmH5s5-R_noVy41kgFDrSMes3DLiAX3fb1EcF7FsLaySv0Q',2,1,'2024-08-09 12:49:15','2024-08-09 12:49:15'),(20,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMDg2NjQsImV4cCI6MTcyMzgxMzQ2NH0.dNvYviwwpfIf6qQ7rkeIPufOgjwqkyTdbbB3QvUIg8NJYHD2B1x1g12DGMEWNYki8vDdXlahyX3WTzHSpucZ_A',2,1,'2024-08-09 13:04:24','2024-08-09 13:04:24'),(21,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEyIiwiaWF0IjoxNzIzMjEzNTY3LCJleHAiOjE3MjM4MTgzNjd9.rRAMBBYggGrRSm91-_vjDsd-dEqc1_1izceNBVyl3rD3ThgiOwuVUIJbrGoTLphhuai6XLxOa2jTMtsG2Nt-9A',3,0,'2024-08-09 14:26:07','2024-08-09 14:26:07'),(22,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyMTM2MTAsImV4cCI6MTcyMzgxODQxMH0.6rgR2F3ybC1v_jCdcKFSZZUbQDsOFRi8KdO0f9zF8r38rkdzimexl29cen0lj53WoouCvjlUiOURMQSOkzzesg',2,1,'2024-08-09 14:26:50','2024-08-09 14:26:50'),(23,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGEiLCJpYXQiOjE3MjMyNTAyNDUsImV4cCI6MTcyMzg1NTA0NX0.JE58Z7kVxk5rMq-IU145exCi_0fzHOafJqaXdyU29ZnuYQwRRdRbD2Qbx-i3rV9rCNCZPboCDCfe_DLlZ9YmwA',2,0,'2024-08-10 00:37:25','2024-08-10 00:37:25');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `inserted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nhgn2','nhann9642@gmail.com','nhan','nguyen','$2a$10$zPr2.I3hWOXe3B/1IZKAP.jfOMStQm4E/BW4YOPsXO2FtYcFzM8F.','ROLE_USER','2024-07-01 11:09:11','2024-07-01 11:09:11'),(2,'nha','nhaan9642@gmail.com','Nham','Nguyen','$2a$10$mFZ6XvEelfhPiSJbk.qAm./PZ51uljI0O2eP3o9UWIdwAxJ5Cfo6a','ROLE_ADMIN','2024-07-01 16:00:31','2024-07-01 16:00:31'),(3,'nha2','nha2@gmail.com','nhan','nguyen','$2a$10$STgR6oes0CFdohIw.s1h4.VWBVwGWTWN7yLOxFNG7lUFwmynt/JRS','ROLE_USER','2024-07-07 02:53:02','2024-07-07 02:53:02'),(4,'nha3','nha3@gmail.com','nhan','nguyen','$2a$10$.PMDJXkWAgYv1.Xl7922heBb1YiLiZ8rOF0WuRPdruO9ipjVBPxM6','ROLE_USER','2024-07-07 02:53:13','2024-07-07 02:53:13'),(5,'nha4','nha4@gmail.com','nhan','nguyen','$2a$10$t8c/KkslnhHDR7cBq6UmSerCtpudAkGNmEgV.jaVJwpVt6L.JlzOm','ROLE_USER','2024-07-07 02:53:21','2024-07-07 02:53:21');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-11  8:44:43
