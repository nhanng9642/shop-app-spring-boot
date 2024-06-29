INSERT INTO category (category_name, description) VALUES
  ('Autobiography', 'Life stories written by the person themselves.'),
  ('Memoir', 'Personal narratives focusing on significant events and memories.'),
  ('Thriller', 'Suspenseful stories often involving mysteries, crimes, or dark plots.'),
  ('Fiction', 'Imaginary worlds, characters, and events that captivate readers.'),
  ('Fantasy', 'Magical realms, mythical creatures, and boundless adventures.'),
  ('History', 'Accounts of the past, encompassing historical events, figures, and cultures.'),
  ('Self-help', 'Guides providing knowledge and methods for self-improvement.'),
  ('Psychology', 'Studies exploring human behavior, thought processes, and mental states.');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('Becoming', 1, 'Michelle Obama', 'Crown Publishing Group', '2018', 28.99, 60, 'Autobiography of the former First Lady.', 'becoming.jpg'),
    ('Educated: A Memoir', 2, 'Tara Westover', 'Random House', '2018', 24.50, 2, 'Memoir about a woman who leaves her survivalist family.', 'educated.jpg'),
    ('The Girl on the Train', 3, 'Paula Hawkins', 'Riverhead Books', '2015', 19.75, 70, 'Thriller novel with a gripping narrative.', 'girl_on_train.jpg'),
    ('The Great Gatsby', 4, 'F. Scott Fitzgerald', 'Scribner', '1925', 25.99, 50, 'Classic fiction novel about the American Dream.', 'great_gatsby.jpg'),
    ('Harry Potter and the Sorcerer''s Stone', 5, 'J.K. Rowling', 'Bloomsbury Publishing', '1997', 30.00, 80, 'Fantasy novel introducing the wizarding world.', 'harry_potter.jpg'),
    ('The Power of Now', 6, 'Eckhart Tolle', 'New World Library', '1997', 15.99, 55, 'Self-help book on spiritual growth and mindfulness.', 'power_of_now.jpg'),
    ('Sapiens: A Brief History of Humankind', 7, 'Yuval Noah Harari', 'Harper', '2014', 21.50, 65, 'Historical exploration of the human species.', 'sapiens.jpg'),
    ('Thinking, Fast and Slow', 8, 'Daniel Kahneman', 'Farrar, Straus and Giroux', '2011', 22.75, 40, 'Psychology book about decision-making processes.', 'thinking_fast_slow.jpg'),
    ('The Alchemist', 5, 'Paulo Coelho', 'HarperOne', '1988', 20.99, 75, 'Fiction novel about a young shepherd on a journey.', 'alchemist.jpg');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('The Diary of a Young Girl', 4, 'Anne Frank', 'Contact Publishing', '1947', 17.99, 55, 'Autobiographical account of a Jewish girl during WWII.', 'diary_young_girl.jpg'),
    ('Born a Crime', 1, 'Trevor Noah', 'Spiegel & Grau', '2016', 23.50, 40, 'Memoir about Trevor Noah''s childhood in South Africa.', 'born_a_crime.jpg'),
    ('The Da Vinci Code', 3, 'Dan Brown', 'Doubleday', '2003', 18.75, 65, 'Thriller involving hidden secrets and codes.', 'da_vinci_code.jpg'),
    ('Animal Farm', 4, 'George Orwell', 'Secker & Warburg', '1945', 16.99, 70, 'Allegorical novella about the Russian Revolution.', 'animal_farm.jpg'),
    ('The Hobbit', 5, 'J.R.R. Tolkien', 'Allen & Unwin', '1937', 19.99, 75, 'Fantasy adventure novel set in Middle-earth.', 'hobbit.jpg'),
    ('The Subtle Art of Not Giving a F*ck', 6, 'Mark Manson', 'HarperOne', '2016', 21.25, 50, 'Self-help book about focusing on what truly matters.', 'subtle_art.jpg'),
    ('Guns, Germs, and Steel', 7, 'Jared Diamond', 'W.W. Norton & Company', '1997', 24.50, 60, 'Historical analysis of human societies and development.', 'guns_germs_steel.jpg'),
    ('Man''s Search for Meaning', 8, 'Viktor E. Frankl', 'Beacon Press', '1946', 20.75, 45, 'Psychology book detailing Frankl''s experiences in Auschwitz.', 'mans_search_meaning.jpg'),
    ('The Catcher in the Rye', 4, 'J.D. Salinger', 'Little, Brown and Company', '1951', 18.99, 4, 'Classic novel following Holden Caulfield''s experiences.', 'catcher_in_rye.jpg'),
    ('The Secret', 6, 'Rhonda Byrne', 'Atria Books', '2006', 19.25, 55, 'Self-help book discussing the law of attraction.', 'the_secret.jpg');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('The Brothers Karamazov', 4, 'Fyodor Dostoevsky', 'The Russian Messenger', '1880', 26.99, 30, 'A complex novel weaving themes of morality, spirituality, and family dynamics, following the lives of the Karamazov brothers in 19th-century Russia.', 'brothers_karamazov.jpg'),
    ('Bossypants', 1, 'Tina Fey', 'Reagan Arthur Books', '2011', 18.50, 40, 'A comedic memoir by Tina Fey, reflecting on her life, career, and experiences in the entertainment industry.', 'bossypants.jpg'),
    ('Gone Girl', 3, 'Gillian Flynn', 'Crown Publishing Group', '2012', 20.25, 55, 'A psychological thriller revolving around the mysterious disappearance of Amy Dunne and the ensuing investigation that uncovers hidden truths about marriage and relationships.', 'gone_girl.jpg'),
    ('The Picture of Dorian Gray', 4, 'Oscar Wilde', 'Lippincott''s Monthly Magazine', '1890', 22.75, 25, 'A philosophical novel exploring the consequences of vanity and the pursuit of eternal youth, as depicted through the life of Dorian Gray.', 'dorian_gray.jpg'),
    ('The Name of the Wind', 5, 'Patrick Rothfuss', 'DAW Books', '2007', 24.99, 35, 'An epic fantasy following the life of Kvothe, a magically gifted young man, as he recounts his journey to becoming a legendary figure.', 'name_of_wind.jpg'),
    ('You Are a Badass', 6, 'Jen Sincero', 'Running Press', '2013', 19.99, 45, 'A motivational self-help book encouraging readers to embrace their inner potential and live life fearlessly.', 'you_are_badass.jpg'),
    ('1491: New Revelations of the Americas Before Columbus', 7, 'Charles C. Mann', 'Knopf', '2005', 23.50, 50, 'A historical exploration challenging conventional notions about the Americas before the arrival of Christopher Columbus, shedding light on the complexity and sophistication of indigenous societies.', '1491.jpg'),
    ('Quiet: The Power of Introverts in a World That Can''t Stop Talking', 8, 'Susan Cain', 'Crown Publishing Group', '2012', 21.50, 60, 'A psychological examination of introversion, highlighting its strengths and advantages in a society that often values extroversion.', 'quiet.jpg'),
    ('The Road', 4, 'Cormac McCarthy', 'Alfred A. Knopf', '2006', 20.99, 30, 'A post-apocalyptic novel following the journey of a father and son through a desolate landscape, exploring themes of survival, hope, and humanity.', 'the_road.jpg'),
    ('The 7 Habits of Highly Effective People', 6, 'Stephen R. Covey', 'Free Press', '1989', 25.25, 55, 'A self-help book presenting a holistic approach to personal and professional effectiveness based on seven habits that foster growth and success.', '7_habits.jpg');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('The Martian', 4, 'Andy Weir', 'Crown Publishing Group', '2014', 18.99, 60, 'Science fiction novel following an astronaut stranded on Mars and his struggle for survival.', 'the_martian.jpg'),
    ('Me Talk Pretty One Day', 1, 'David Sedaris', 'Little, Brown and Company', '2000', 17.50, 3, 'Humorous autobiographical essays reflecting on the Author''s experiences and observations.', 'me_talk_pretty.jpg'),
    ('The Girl with the Dragon Tattoo', 3, 'Stieg Larsson', 'Norstedts Förlag', '2005', 19.75, 55, 'Mystery thriller featuring journalist Mikael Blomkvist and hacker Lisbeth Salander investigating a disappearance.', 'girl_dragon_tattoo.jpg'),
    ('Brave New World', 4, 'Aldous Huxley', 'Chatto & Windus', '1932', 20.25, 50, 'Dystopian novel exploring a future society controlled by technology, genetic engineering, and conditioning.', 'brave_new_world.jpg'),
    ('The Name of the Rose', 4, 'Umberto Eco', 'Bompiani', '1980', 21.99, 40, 'Historical mystery set in an Italian monastery during the Middle Ages, involving murder and theological debate.', 'name_of_rose.jpg'),
    ('The Goldfinch', 4, 'Donna Tartt', 'Little, Brown and Company', '2013', 22.50, 65, 'Coming-of-age novel centered around a young boy who steals a famous painting after a tragic event.', 'goldfinch.jpg'),
    ('The Night Circus', 5, 'Erin Morgenstern', 'Doubleday', '2011', 23.75, 70, 'Fantasy novel featuring a magical competition between two young illusionists.', 'night_circus.jpg'),
    ('The Power', 4, 'Naomi Alderman', 'Viking', '2016', 19.99, 60, 'Science fiction novel exploring a world where women develop the ability to generate electrical power.', 'the_power.jpg'),
    ('The Immortal Life of Henrietta Lacks', 1, 'Rebecca Skloot', 'Crown Publishing Group', '2010',  20.25, 50, 'Non-fiction book chronicling the story of Henrietta Lacks and the immortal cell line known as HeLa.', 'immortal_life.jpg');


INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('The Hitchhiker''s Guide to the Galaxy', 4, 'Douglas Adams', 'Pan Books', '1979', 18.50, 60, 'A comedic science fiction series following the misadventures of Arthur Dent.', 'hitchhikers_guide.jpg'),
    ('Pride and Prejudice', 4, 'Jane Austen', 'T. Egerton, Whitehall', '1813', 22.99, 30, 'A classic romance novel.', 'pride_and_prejudice.jpg'),
    ('The Lord of the Rings', 5, 'J.R.R. Tolkien', 'George Allen & Unwin', '1954', 28.00, 80, 'Epic fantasy novel following the quest to destroy the One Ring.', 'lord_of_rings.jpg'),
    ('Crime and Punishment', 4, 'Fyodor Dostoevsky', 'The Russian Messenger', '1866', 19.25, 2, 'A psychological thriller following the moral dilemmas of Raskolnikov.', 'crime_and_punishment.jpg');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('The Autobiography of Malcolm X', 1, 'Malcolm X', 'Ballantine Books', '1965', 25.99, 10, 'An autobiography of Malcolm X, an American Muslim minister and human rights activist.', 'malcolm_x_autobiography.jpg'),
    ('Memoirs of a Geisha', 2, 'Arthur Golden', 'Knopf Doubleday Publishing Group', '1997', 16.99, 5, 'A memoir of a Japanese geisha, Chiyo Sakura.', 'memoirs_of_a_geisha.jpg'),
    ('American Gods', 5, 'Neil Gaiman', 'William Morrow & Company', '2001', 17.99, 9, 'A fantasy novel about a shadow war brewing between Old World gods and New World gods.', 'american_gods.jpg'),
    ('A Short History of Nearly Everything', 7, 'Bill Bryson', 'Broadway Books', '2003', 14.99, 4, 'A history book that covers a wide range of topics, from the Big Bang to the human body.', 'a_short_history_of_nearly_everything.jpg');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('The Road Less Traveled', 6, 'M. Scott Peck', 'Simon & Schuster', '1978', 20.50, 60, 'A self-help book exploring personal growth and spiritual development.', 'road_less_traveled.jpg'),
    ('The Shining', 4, 'Stephen King', 'Doubleday', '1977', 19.99, 55, 'A horror novel about a haunted hotel and a writer’s descent into madness.', 'the_shining.jpg'),
    ('Anna Karenina', 4, 'Leo Tolstoy', 'The Russian Messenger', '1878', 23.50, 50, 'A classic novel depicting tragic love and societal norms in 19th-century Russia.', 'anna_karenina.jpg'),
    ('The Grapes of Wrath', 4, 'John Steinbeck', 'The Viking Press', '1939', 21.25, 45, 'A novel portraying the hardships faced by migrant farmers during the Great Depression.', 'grapes_of_wrath.jpg'),
    ('The Stranger', 4, 'Albert Camus', 'Gallimard', '1942', 18.99, 40, 'A philosophical novel exploring themes of existentialism and absurdity.', 'the_stranger.jpg'),
    ('A Brief History of Time', 7, 'Stephen Hawking', 'Bantam Books', '1988', 25.99, 65, 'A popular science book discussing cosmology, black holes, and the universe.', 'brief_history_time.jpg'),
    ('The Girl with the Pearl Earring', 4, 'Tracy Chevalier', 'HarperCollins', '1999', 17.99, 70, 'Historical novel inspired by Vermeer’s painting of the same name.', 'girl_pearl_earring.jpg'),
    ('The Hunger Games', 5, 'Suzanne Collins', 'Scholastic Corporation', '2008', 22.75, 75, 'A dystopian novel following a young woman’s fight for survival in a televised competition.', 'hunger_games.jpg'),
    ('The Joy Luck Club', 4, 'Amy Tan', 'G.P. Putnam’s Sons', '1989', 20.99, 60, 'Novel exploring the relationships between Chinese-American immigrant families.', 'joy_luck_club.jpg'),
    ('Siddhartha', 1, 'Hermann Hesse', 'S. Fischer Verlag', '1922', 18.50, 55, 'A novel exploring the spiritual journey of a man named Siddhartha.', 'siddhartha.jpg');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('1984', 4, 'George Orwell', 'Secker & Warburg', '1949', 19.99, 60, 'A dystopian novel portraying a totalitarian society and its manipulation of truth.', '1984.jpg'),
    ('To Kill a Mockingbird', 4, 'Harper Lee', 'J. B. Lippincott & Co.', '1960', 21.50, 50, 'A classic novel addressing racial injustice and moral growth.', 'to_kill_mockingbird.jpg'),
    ('The Book of Five Rings', 7, 'Miyamoto Musashi', 'Various', '1645', 16.75, 55, 'A Japanese text on martial arts strategy and philosophy.', 'book_five_rings.jpg'),
    ('The Count of Monte Cristo', 4, 'Alexandre Dumas', 'Ponson du Terrail', '1844', 23.99, 40, 'Adventure novel following a man seeking vengeance and redemption.', 'count_monte_cristo.jpg'),
    ('One Hundred Years of Solitude', 4, 'Gabriel García Márquez', 'Editorial Sudamericana', '1967', 25.25, 45, 'Magical realism novel chronicling the Buendía family over generations.', 'one_hundred_years.jpg'),
    ('The Wind-Up Bird Chronicle', 4, 'Haruki Murakami', 'Shinchosha', '1994', 22.99, 55, 'Surreal novel exploring themes of loss, identity, and human connection.', 'wind_up_bird_chronicle.jpg'),
    ('The Stand', 4, 'Stephen King', 'Doubleday', '1978', 20.75, 65, 'Post-apocalyptic novel depicting a world devastated by a pandemic.', 'the_stand.jpg'),
    ('The Sun Also Rises', 4, 'Ernest Hemingway', 'Scribner', '1926', 18.50, 60, 'A novel capturing the disillusionment of the Lost Generation after World War I.', 'sun_also_rises.jpg'),
    ('The Art of Happiness', 8, 'Dalai Lama XIV, Howard Cutler', 'Riverhead Books', '1998', 17.99, 70, 'A book exploring the principles of happiness and well-being.', 'art_of_happiness.jpg'),
    ('The Secret Life of Bees', 4, 'Sue Monk Kidd', 'Viking Press', '2002', 19.25, 65, 'Novel about a young girl in South Carolina finding solace in beekeeping.', 'secret_life_bees.jpg');


INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('Moby-Dick', 4, 'Herman Melville', 'Richard Bentley', '1851', 24.99, 40, 'Novel exploring themes of obsession and revenge through the story of Captain Ahab and the white whale.', 'moby_dick.jpg'),
    ('The Little Prince', 4, 'Antoine de Saint-Exupéry', 'Reynal & Hitchcock', '1943', 19.75, 60, 'Fable about a young prince exploring the complexities of human nature.', 'little_prince.jpg'),
    ('Frankenstein', 4, 'Mary Shelley', 'Lackington, Hughes, Harding, Mavor, & Jones', '1818', 18.50, 50, 'Gothic novel exploring the consequences of scientific ambition and creation.', 'frankenstein.jpg'),
    ('Wuthering Heights', 4, 'Emily Brontë', 'Thomas Cautley Newby', '1847', 20.25, 45, 'A tale of love, revenge, and the destructive power of nature.', 'wuthering_heights.jpg'),
    ('The Adventures of Sherlock Holmes', 4, 'Arthur Conan Doyle', 'George Newnes Ltd', '1892', 17.99, 60, 'Collection of detective stories featuring Sherlock Holmes.', 'adventures_sherlock_holmes.jpg');

INSERT INTO book (title, category_id, author, publisher, published_year, price, quantity_available, description, book_image)
VALUES
    ('Dracula', 3, 'Bram Stoker', 'Archibald Constable and Company', '1897', 18.99, 40, 'Classic Gothic horror novel about Count Dracula''s attempt to move from Transylvania to England.', 'dracula.jpg'),
    ('The Exorcist', 3, 'William Peter Blatty', 'Harper & Row', '1971', 20.50, 55, 'A novel about the demonic possession of a young girl and the attempts to exorcise the demon.', 'exorcist.jpg'),
    ('The Haunting of Hill House', 3, 'Shirley Jackson', 'Viking Press', '1959', 22.99, 50, 'A novel exploring the supernatural events that occur at Hill House.', 'haunting_hill_house.jpg'),
    ('Pet Sematary', 3, 'Stephen King', 'Doubleday', '1983', 19.75, 60, 'Horror novel about a cemetery that brings the dead back to life.', 'pet_sematary.jpg'),
    ('American Psycho', 3, 'Bret Easton Ellis', 'Vintage Books', '1991', 23.50, 55, 'A psychological horror novel following an investment banker who is also a serial killer.', 'american_psycho.jpg'),
    ('House of Leaves', 3, 'Mark Z. Danielewski', 'Pantheon Books', '2000', 24.99, 40, 'A complex horror novel with unconventional formatting, exploring a house that defies the laws of physics.', 'house_of_leaves.jpg'),
    ('The Girl Next Door', 3, 'Jack Ketchum', 'Macabre Ink', '1989', 20.25, 50, 'A horror novel based on a true story of abuse and torment in suburban America.', 'girl_next_door.jpg'),
    ('Hell House', 3, 'Richard Matheson', 'Viking Press', '1971', 18.99, 65, 'A haunted house novel where a physicist investigates supernatural occurrences.', 'hell_house.jpg'),
    ('The Call of Cthulhu and Other Weird Stories', 3, 'H.P. Lovecraft', 'Arkham House', '1928', 17.50, 60, 'A collection of Lovecraftian horror stories featuring cosmic entities and ancient horrors.', 'call_of_cthulhu.jpg');

INSERT INTO customer (name, email, address, phone)
VALUES
    (N'Nguyễn Văn Nam', 'nguyenvannam@email.com',	N'Số 31, Đường Nguyễn Du, Quận 1, Thành phố Hồ Chí Minh', '0987654321'),
    (N'Trần Thị Hương', 'tranthihuongs@email.com',	N'Số 15, Đường Lê Lợi, Quận Hai Bà Trưng, Thành phố Hà Nội', '0903123456'),
    (N'Lê Thị Mai', 'lethimai@email.com',			N'Số 9, Đường Hải Phòng, Quận Hải Châu, Thành phố Đà Nẵng', '0978234567'),
    (N'Phạm Văn Tuấn', 'phamvantuan@email.com',		N'Số 11, Đường Trần Phú, Quận Ninh Kiều, Thành phố Cần Thơ', '0912345678'),
    (N'Hoàng Minh Anh', 'hoangminhanh@email.com',	N'Số 50, Đường Hồng Bàng, Quận Hải An, Thành phố Hải Phòng', '0965432109'),
    (N'Trần Thanh Hải', 'tranthanhhai@email.com',	N'Số 32, Đường Bà Rịa - Vũng Tàu, Quận Nguyễn An Ninh, Thành phố Vũng Tàu', '0934567890'),
    (N'Nguyễn Thị Lan', 'nguyenthilan@email.com',	N'Số 76, Đường Phan Đình Phùng, Quận Biên Hòa, Thành phố Biên Hòa', '0956789012'),
    (N'Lê Văn Thành', 'levanthanh@email.com',		N'Số 80, Đường Trần Hưng Đạo, Quận Lê Lợi, Thành phố Qui Nhơn', '0912345678'),
    (N'Trần Thị Thu', 'tranthithu@email.com',		N'Số 51, Đường Nguyễn Thị Minh Khai, Quận Lộc Thọ, Thành phố Nha Trang', '0945678901'),
    (N'Hoàng Văn Long', 'hoangvanlong@email.com',	N'Số 254, Đường Lê Lai, Quận Mỹ Thới, Thành phố Long Xuyên', '0987654321');

INSERT INTO customer (name, email, address, phone)
VALUES
    (N'Nguyễn Thị Hằng', 'nguyenthihang@email.com',		N'Số 110, Đường Võ Thị Sáu, Quận Thanh Khê, Thành phố Đà Nẵng', '0901234567'),
    (N'Trần Văn Đông', 'tranvandong@email.com',			N'Số 324, Đường Lê Hồng Phong, Quận Ninh Kiều, Thành phố Cần Thơ', '0976543210'),
    (N'Lê Thị Ngọc', 'lethingoc@email.com',				N'Số 131, Đường Trần Quang Khải, Quận Hải Châu, Thành phố Đà Nẵng', '0918765432'),
    (N'Phạm Văn Hùng', 'phamvanhung@email.com',			N'Số 928, Đường Hai Bà Trưng, Quận Hoàn Kiếm, Thành phố Hà Nội', '0987654321'),
    (N'Hoàng Thị Hồng', 'hoangthihong@email.com', N'Số 112, Đường Nguyễn Văn Linh, Quận Hải Châu, Thành phố Đà Nẵng', '0965432109'),
    (N'Trần Văn Minh', 'tranvanminh@email.com', N'Số 125, Đường Lê Duẩn, Quận Hải Châu, Thành phố Đà Nẵng', '0943210987'),
    (N'Nguyễn Thị Lệ', 'nguyenthile@email.com', N'Số 26, Đường Nguyễn Tri Phương, Quận Thanh Khê, Thành phố Đà Nẵng', '0921098765'),
    (N'Lê Văn Hoàng', 'levanhoang@email.com', N'Số 190, Đường Nguyễn Hữu Thọ, Quận Liên Chiểu, Thành phố Đà Nẵng', '0912345678'),
    (N'Trần Thị Quỳnh', 'tranthiquynh@email.com', N'Số 198, Đường Nguyễn Văn Linh, Quận Sơn Trà, Thành phố Đà Nẵng', '0987654321'),
    (N'Hoàng Văn Tiến', 'hoangvantien@email.com', N'Số 201, Đường Hà Huy Tập, Quận Thanh Khê, Thành phố Đà Nẵng', '0976543210'),
    (N'Nguyễn Thị Thảo', 'nguyenthithao@email.com', N'Số 214, Đường Lý Tự Trọng, Quận Hải Châu, Thành phố Đà Nẵng', '0901234567'),
    (N'Lê Văn Trung', 'levantrung@email.com', N'Số 421, Đường Trần Phú, Quận Hải Châu, Thành phố Đà Nẵng', '0976543210'),
    (N'Trần Thị Hà', 'tranthiha@email.com', N'Số 232, Đường Hùng Vương, Quận Thanh Khê, Thành phố Đà Nẵng', '0943210987'),
    (N'Phạm Văn Quân', 'phamvanquan@email.com', N'Số 241, Đường Nguyễn Chí Thanh, Quận Hải Châu, Thành phố Đà Nẵng', '0921098765'),
    (N'Nguyễn Thị Mỹ', 'nguyenthimy@email.com', N'Số 255, Đường Nguyễn Tri Phương, Quận Thanh Khê, Thành phố Đà Nẵng', '0912345678'),
    (N'Lê Văn Dũng', 'levandung@email.com', N'Số 262, Đường Lê Lai, Quận Hải Châu, Thành phố Đà Nẵng', '0987654321'),
    (N'Trần Thị Ngọc', 'tranthingoc@email.com', N'Số 127, Đường Lê Đình Dương, Quận Thanh Khê, Thành phố Đà Nẵng', '0976543210'),
    (N'Hoàng Văn Đức', 'hoangvanduc@email.com', N'Số 282, Đường Phạm Văn Đồng, Quận Hải Châu, Thành phố Đà Nẵng', '0901234567'),
    (N'Nguyễn Thị Thanh', 'nguyenthithanh@email.com', N'Số 329, Đường Nguyễn Tất Thành, Quận Hải Châu, Thành phố Đà Nẵng', '0943210987'),
    (N'Lê Văn Tuấn', 'levantuan@email.com', N'Số 302, Đường Trần Quốc Toản, Quận Hải Châu, Thành phố Đà Nẵng', '0921098765');

INSERT INTO customer (name, email, address, phone)
VALUES
    (N'Nguyễn Văn Hoàng', 'nguyenvanhoang@email.com',		N'Số 92, Đường Lê Lợi, Quận Hai Bà Trưng, Thành phố Hà Nội', '0987654321'),
    (N'Trần Thị Lan Anh', 'tranthilanh@email.com',			N'Số 32, Đường Nguyễn Thị Minh Khai, Quận 1, Thành phố Hồ Chí Minh', '0903123456'),
    (N'Lê Văn Đức', 'levanduc@email.com',					N'Số 333, Đường Võ Văn Tần, Quận 3, Thành phố Hồ Chí Minh', '0978234567'),
    (N'Phạm Thị Thúy', 'phamthithuy@email.com',				N'Số 198, Đường Trần Hưng Đạo, Quận Hoàn Kiếm, Thành phố Hà Nội', '0912345678'),
    (N'Hoàng Văn Trọng', 'hoangvantrong@email.com',			N'Số 207, Đường Lý Thường Kiệt, Quận Hải Châu, Thành phố Đà Nẵng', '0965432109'),
    (N'Trần Thị Phương', 'tranthiphuong@email.com',			N'Số 119, Đường Nguyễn Văn Linh, Quận Thanh Khê, Thành phố Đà Nẵng', '0934567890'),
    (N'Nguyễn Văn Bình', 'nguyenvanbinh@email.com',			N'Số 203, Đường Nguyễn Hữu Thọ, Quận Sơn Trà, Thành phố Đà Nẵng', '0956789012'),
    (N'Lê Thị Thu Hà', 'lethithuha@email.com',				N'Số 97, Đường Hải Phòng, Quận Liên Chiểu, Thành phố Đà Nẵng', '0912345678'),
    (N'Trần Văn Tuấn Anh', 'tranvantuananh@email.com',		N'Số 332, Đường Nguyễn Tri Phương, Quận Hải Châu, Thành phố Đà Nẵng', '0945678901'),
    (N'Hoàng Thị Thùy Dung', 'hoangthithuydung@email.com',	N'Số 32, Đường Trần Phú, Quận Hải Châu, Thành phố Đà Nẵng', '0987654321');

INSERT INTO customer (name, email, address, phone)
VALUES
    (N'Nguyễn Thị Kim Anh', 'nguyenthikimanh@email.com',	N'Số 456, Đường Nguyễn Văn Linh, Quận 1, Thành phố Hồ Chí Minh', '0901234567'),
    (N'Trần Văn Đạt', 'tranvandat@email.com',				N'Số 789, Đường Lê Lợi, Quận Hai Bà Trưng, Thành phố Hà Nội', '0976543210'),
    (N'Lê Thị Thanh Hương', 'lethithanhhuong@email.com',	N'Số 234, Đường Bạch Đằng, Quận Hải Châu, Thành phố Đà Nẵng', '0918765432'),
    (N'Phạm Văn Hải', 'phamvanhai@email.com',				N'Số 567, Đường Trần Phú, Quận Cẩm Lệ, Thành phố Đà Nẵng', '0987654321'),
    (N'Hoàng Thị Thảo Vy', 'hoangthithaovy@email.com',		N'Số 890, Đường Hùng Vương, Quận Thanh Khê, Thành phố Đà Nẵng', '0965432109'),
    (N'Trần Văn Minh Đức', 'tranvanminhduc@email.com',		N'Số 123, Đường Đống Đa, Quận Thanh Khê, Thành phố Đà Nẵng', '0943210987'),
    (N'Nguyễn Thùy Trang', 'nguyenthuytrang@email.com',		N'Số 456, Đường Nguyễn Văn Linh, Quận Hải Châu, Thành phố Đà Nẵng', '0921098765'),
    (N'Lê Văn Khánh', 'levankhanh@email.com',				N'Số 789, Đường 2/9, Quận Hải Châu, Thành phố Đà Nẵng', '0912345678'),
    (N'Trần Thị Quỳnh Nga', 'tranthiquynhnga@email.com',	N'Số 234, Đường Trần Phú, Quận Thanh Khê, Thành phố Đà Nẵng', '0987654321'),
    (N'Hoàng Văn Tiến Dũng', 'hoangvantiendoong@email.com', N'Số 567, Đường Lê Lợi, Quận Liên Chiểu, Thành phố Đà Nẵng', '0976543210');

select * from customer;