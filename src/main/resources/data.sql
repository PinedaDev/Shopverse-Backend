CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO _products (product_id,name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
  'Adidas Ultraboost Light',
  'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Adidas-Ultraboost-Light_a7sept.png',
  'Experience epic energy with the new Ultraboost Light, our lightest Ultraboost ever. The magic lies in the Light BOOST midsole, a new generation of adidas BOOST. Its unique molecule design achieves the lightest BOOST foam to date. With hundreds of BOOST capsules bursting with energy and ultimate cushioning and comfort, some feet really can have it all.',
  '{"sports", "running"}',
  '{"red", "black"}',
  '{38, 39, 41, 42}',
  140.0,
  423,
  4,
  NOW()
);

INSERT INTO _products (product_id, name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
    'Nike Air Jordan Mid',
    'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Nike-Air-Jordan-Mid_kh4jum.png',
    'Inspired by the original AJ1, the Air Jordan 1 Mid offers fans a chance to follow in MJ''s footsteps. Fresh colour trims the clean, classic materials, imbuing modernity into a classic design.',
    '{"running"}',
    '{"black", "white"}',
    '{40, 43, 46}',
    110.0,
    354,
    5,
    NOW()
);
-- Product: Nike Dunk Low
INSERT INTO _products (product_id, name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
    'Nike Dunk Low',
    'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Nike-Dunk-Low_ctetbp.png',
    'Created for the hardwood but taken to the streets, the Dunk returns with classic details and throwback hoops style. Channelling a vintage design back onto the streets, its padded, low-cut collar lets you comfortably take your game anywhere.',
    '{"sneaker"}',
    '{"blue", "black", "white"}',
    '{42, 44, 45}',
    170.0,
    547,
    4,
    NOW()
);

-- Product: Nike Dunk High Retro
INSERT INTO _products (product_id, name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
    'Nike Dunk High Retro',
    'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Nike-Dunk-High-Retro_gz2ekh.png',
    'Created for the hardwood but taken to the streets, the ''80s basketball icon returns with perfectly sheened overlays and original university colours. With its classic hoops design, the Nike Dunk High Retro channels ''80s vintage back onto the streets while its padded, high-top collar adds an old-school look rooted in comfort.',
    '{"sneaker"}',
    '{"white", "black"}',
    '{40, 41, 42}',
    140.0,
    987,
    4,
    NOW()
);

-- Product: Puma Anzarun Lite
INSERT INTO _products (product_id, name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
    'Puma Anzarun Lite',
    'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Puma-Anzarun-Lite_ph1o92.png',
    'The freshest look for any occasion, the Anzarun Lite Trainers are PUMA''s most refined shoe yet. Featuring the breezy Anzarun DNA mesh upper, a cushy SoftFoam+ sockliner and discreet PUMA branding throughout, you''re sure to look great, wherever the day takes you.',
    '{"sneaker"}',
    '{"white", "black"}',
    '{38, 39, 40}',
    120.0,
    247,
    4,
    NOW()
);

-- Product: Puma Carina Street
INSERT INTO _products (product_id, name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
    'Puma Carina Street',
    'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Puma-Carina-Street_ndpa5v.png',
    'Streetwear-inspired design and a slightly elevated platform make these Carina''s perfect for everyday outfits.',
    '{"sneaker"}',
    '{"white"}',
    '{38, 39, 40}',
    125.0,
    735,
    3,
    NOW()
);

-- Product: Puma Cassia
INSERT INTO _products (product_id, name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
    'Puma Cassia',
    'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Puma-Cassia-_f0x2ur.png',
    'Break the mould with this first-of-its-kind design. Our Cassia dresses up a progressive, feminine silhouette with a mesh base and slightly oversized leather panels for a look that epitomises contemporary fashion. Our Cassia trainers pick up where our Cilia trainers left off, innovating with an unmistakable upper, ultra comfortable compression-moulded EVA midsole and a SoftFoam+ sockliner for instant step-in and all day comfort.',
    '{"sneaker"}',
    '{"white"}',
    '{37, 38, 39}',
    75.0,
    645,
    5,
    NOW()
);

-- Product: Puma X-Ray
INSERT INTO _products (product_id, name, img, description, categories, colors, sizes, price, reviews, stars, created_at)
VALUES (
    uuid_generate_v4(),
    'Puma X-Ray',
    'https://res.cloudinary.com/disdhrmgt/image/upload/v1685050596/Puma-X-Ray-Square_xqcdgf.png',
    'For the fashion-forward sneakerhead, we turn our sights to the early 2000s and our celebrated X-Ray. This silhouette features on-trend chunky details, while innovating with a mix of materials and sleek tooling that make it a memorable next step in fashion evolution.',
    '{"sneaker"}',
    '{"white", "black"}',
    '{40, 41, 42}',
    85.0,
    987,
    4,
    NOW()
);