const sadCatPics = [
    "/images/sad_cats/1.jpg",
    "/images/sad_cats/2.jpg",
    "/images/sad_cats/3.jpg",
    "/images/sad_cats/4.jpg",
    "/images/sad_cats/5.jpg",
    "/images/sad_cats/6.jpg",
    "/images/sad_cats/7.jpg",
    "/images/sad_cats/8.jpg",
    "/images/sad_cats/9.jpg",
    "/images/sad_cats/10.jpg"
];

const placeholderSrc = () => {
    return sadCatPics[Math.floor(Math.random() * sadCatPics.length)];
}

export default placeholderSrc;