INSERT INTO roles values(1, 'USER'),(2, 'ADMIN');

INSERT INTO users (id, age, description, email, full_name, image_url, level, password, username)
VALUES (
        1,
        '20',
        'I have progressed in the sport and I can''t wait to develop in the future.',
        'dzansudikmemehmed@gmail.com',
        'Dzhansu',
        'https://softuni.bg/trainings/resources/video/101217/video-25-june-2024-lachezar-balev-spring-advanced-june-2024/4532',
        'INTERMEDIATE',
        'b7066bf83d137f5e18cc827fc13ac08e1ca8cd0437179d0039808a4b198f87cc5e346cf30ec5194bbd0452b9a55b3112',
        'DzhansuD');

INSERT INTO users_roles (user_id, roles_id)
VALUES(1,1),
      (1,2);