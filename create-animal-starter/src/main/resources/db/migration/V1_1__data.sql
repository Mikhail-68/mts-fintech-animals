
begin;

TRUNCATE TABLE animals.animal_type RESTART IDENTITY cascade;
insert into animals.animal_type (type, is_wild) values
('Mammal', false),
('Bird', false),
('Predator', true),
('Reptile', true),
('Amphibian', false),
('Elephantidae', false);

TRUNCATE TABLE animals.animal RESTART IDENTITY cascade;
insert into animals.animal (name, type_id) values
('Lion', 3),
('Tiger', 4),
('Elephant', 6),
('Giraffe', 1),
('Frog', 5);

TRUNCATE TABLE animals.habitat RESTART IDENTITY cascade;
insert into animals.habitat (area) values
('Savannah'),
('Forest'),
('Ocean'),
('Desert'), -- пустыня
('Jungle'); -- джунгли

TRUNCATE TABLE animals.animals_habitats RESTART IDENTITY cascade;
insert into animals.animals_habitats (id_animal, id_habitat) values
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
(5, 5);

TRUNCATE TABLE animals.provider RESTART IDENTITY cascade;
insert into animals.provider (name, phone) values
('Zoo A', '123-456-7890'),
('Zoo B', '987-654-3210'),
('Wildlife Sanctuary', '555-555-5555'),
('Predator Store', '777-777-7777'),
('Animal Shelter', '999-999-9999');

TRUNCATE TABLE animals.animals_providers RESTART IDENTITY cascade;
insert into animals.animals_providers (id_animal, id_provider) values
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(3, 4),
(4, 3),
(5, 5);

end;