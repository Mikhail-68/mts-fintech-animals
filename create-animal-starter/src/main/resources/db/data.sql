
begin;

insert into animals.animal_type (type, is_wild) values
('Mammal', false),
('Bird', false),
('Predator', true),
('Reptile', true),
('Amphibian', false),
('Elephantidae', false);

insert into animals.creature (name, type_id, age) values
('Lion', 3, 5),
('Tiger', 3, 6),
('Elephant', 6, 10),
('Giraffe', 1, 7),
('Frog', 5, 1);

insert into animals.habitat (area) values
('Savannah'),
('Forest'),
('Ocean'),
('Desert'), -- пустыня
('Jungle'); -- джунгли

insert into animals.animals_habitats (id_animal_type, id_area) values
(1, 2),
(1, 5),
(2, 2),
(2, 5),
(3, 1),
(3, 2),
(3, 4),
(4, 5),
(5, 2),
(5, 3),
(5, 5),
(6, 1),
(6, 4);

insert into animals.provider (name, phone) values
('Zoo A', '123-456-7890'),
('Zoo B', '987-654-3210'),
('Wildlife Sanctuary', '555-555-5555'),
('Predator Store', '777-777-7777'),
('Animal Shelter', '999-999-9999');

insert into animals.animals_providers (id_animal_type, id_provider) values
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(3, 4),
(4, 3),
(5, 5),
(6, 3);

end;