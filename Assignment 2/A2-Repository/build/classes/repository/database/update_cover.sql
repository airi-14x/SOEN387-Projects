UPDATE `BookRepo`.`book` SET `image_data` = LOAD_FILE('/Users/Airi/Documents/SOEN387-Projects-and-Labs/Assignment\ 2/A2/tmp/endofownership_photo_final.jpeg') WHERE (`id` = '2');