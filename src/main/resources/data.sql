INSERT INTO categories (category_id, name, image_url)
VALUES
    (1, 'Planting material', 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/category_img/1.jpeg'),
    (2, 'Protective products and septic tanks', 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/category_img/2.jpeg'),
    (3, 'Fertilizer', 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/category_img/3.jpeg'),
    (4, 'Pots and planters', 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/category_img/4.jpeg'),
    (5, 'Tools and equipment', 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/category_img/5.jpeg');

INSERT INTO products (product_id, name, description, price, category_id, image_url, discount_price)
VALUES
    (1, 'Tulip', 'Elevate your garden with our exquisite Tulip planting material...', 2.0, 1, 'https://www.almanac.com/sites/default/files/styles/or/public/image_nodes/tulips-multicolored_0.jpg?itok=5KFk7THG', NULL),
    (2, 'Chamomile', 'Elevate your garden with our premium Chamomile planting material...', 1.7, 1, 'https://files.nccih.nih.gov/chamomile-steven-foster-square.jpg', NULL),
    (3, 'Marigold', 'Ignite your garden with vibrant Marigold blooms. Our superior...', 3.0, 1, 'https://www.gardendesign.com/pictures/images/900x705Max/dream-team-s-portland-garden_6/marigold-flowers-orange-pixabay_12708.jpg', NULL),
    (4, 'Diatomaceous Earth', 'Safeguard your garden with Diatomaceous Earth. A natural...', 10.0, 2, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/29.jpeg', NULL),
    (5, 'Happy Frog', 'Nurture your garden with Happy Frog—your plant''s best friend...', 12.0, 2, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/26.jpeg', NULL),
    (6, 'Horticultural Charcoal', 'Enhance your gardening experience with Horticultural Charcoal...', 13.0, 2, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/28.jpeg', 11.0),
    (7, 'Organic Perlite', 'Fuel your plants'' growth with Organic Perlite. This lightweight...', 6.0, 3, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/24.jpeg', NULL),
    (8, 'Ocean Forest', 'Dive into lush, thriving gardens with Ocean Forest fertilizer...', 7.0, 3, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/27.jpeg', NULL),
    (9, 'Potting Mix', 'Nourish your plants with our Potting Mix fertilizer. Perfectly blended...', 8.5, 3, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/23.jpeg', NULL),
    (10, 'Greek Pot', 'Add a touch of ancient elegance with our Greek Pot. Crafted for...', 30.0, 4, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/1.jpeg', 27.0),
    (11, 'Wicker Pot', 'Embrace natural aesthetics with our Wicker Pot. Stylish and functional...', 20.0, 4, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/12.jpeg', NULL),
    (12, 'Red Pot', 'Make a bold statement in your garden with our vibrant Red Pot. Durable and...', 25.0, 4, 'https://raw.githubusercontent.com/tel-ran-de/telran_backend_garden_shop/master/public/product_img/10.jpeg', NULL),
    (13, 'Shovel', 'Dig into gardening with our sturdy Shovel. Designed for durability and comfort...', 40.0, 5, 'https://assets.leevalley.com/Size4/10115/PG107-radius-ergonomic-stainless-steel-shovel-u-0195.jpg', 34.0),
    (14, 'Rake', 'Maintain a pristine garden with our reliable Rake. Perfect for leaf and debris...', 38.0, 5, 'https://images.ctfassets.net/zma7thmmcinb/46JNtlvxFdhCD2XPHHziLc/31fe4425eff26086a7eb884a4384d85b/find-the-right-rake-plastic-rake.jpg', NULL),
    (15, 'Gardening scissors', 'Precision meets functionality with our Gardening Scissors. Trim and...', 20.0, 5, 'https://cdn.thewirecutter.com/wp-content/uploads/2015/06/pruningshears-2x1-.jpg?auto=webp&quality=75&crop=2:1&width=1024&dpr=2', NULL);
