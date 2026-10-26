(() => {
  const documentRoot = document.documentElement;
  documentRoot.classList.add("js-enabled");

  const menuToggle = document.querySelector(".navbar-toggle");
  const menu = document.querySelector("#navbar-menu");

  const closeMenu = () => {
    if (!menu || !menuToggle) {
      return;
    }
    menu.classList.remove("menu-open");
    menuToggle.setAttribute("aria-expanded", "false");
    menuToggle.setAttribute("aria-label", "Open menu");
  };

  if (menu && menuToggle) {
    menuToggle.addEventListener("click", () => {
      const open = menu.classList.toggle("menu-open");
      menuToggle.setAttribute("aria-expanded", String(open));
      menuToggle.setAttribute("aria-label", open ? "Close menu" : "Open menu");
    });

    document.addEventListener("keydown", (event) => {
      if (event.key === "Escape") {
        closeMenu();
        menuToggle.focus();
      }
    });

    menu.querySelectorAll("a").forEach((link) => {
      link.addEventListener("click", closeMenu);
    });
  }

  const backgroundSlides = [
    "/assets/background/bg1.png",
    "/assets/background/bg2.png",
    "/assets/background/bg3.png",
    "/assets/background/bg4.png",
  ];

  let backgroundIndex = 0;
  const backgroundImages = backgroundSlides.map((src) => {
    const image = new Image();
    image.src = src;
    return image;
  });

  const applyBackground = () => {
    document.body.style.backgroundImage = `url("${backgroundSlides[backgroundIndex]}")`;
    document.body.style.backgroundPosition = "center";
    document.body.style.backgroundRepeat = "no-repeat";
    document.body.style.backgroundSize = "cover";
    document.body.style.backgroundAttachment = "fixed";
  };

  applyBackground();
  window.setInterval(() => {
    backgroundIndex = (backgroundIndex + 1) % backgroundImages.length;
    applyBackground();
  }, 12000);

  const carousel = document.querySelector(".links-carousel");
  if (!carousel) {
    return;
  }

  const track = carousel.querySelector(".links-carousel-track");
  const slides = Array.from(carousel.querySelectorAll(".links-carousel-slide"));
  const dots = Array.from(carousel.querySelectorAll(".links-carousel-dot"));
  const previous = carousel.querySelector("[data-carousel-direction='previous']");
  const next = carousel.querySelector("[data-carousel-direction='next']");

  if (!track || slides.length === 0) {
    return;
  }

  let activeIndex = 0;
  let intervalId;

  const show = (requestedIndex) => {
    activeIndex = (requestedIndex + slides.length) % slides.length;
    track.style.transform = `translateX(-${activeIndex * 100}%)`;

    slides.forEach((slide, index) => {
      const hidden = index !== activeIndex;
      slide.setAttribute("aria-hidden", String(hidden));
      slide.toggleAttribute("inert", hidden);
    });

    dots.forEach((dot, index) => {
      const selected = index === activeIndex;
      dot.classList.toggle("selected", selected);
      dot.setAttribute("aria-selected", String(selected));
      dot.tabIndex = selected ? 0 : -1;
    });
  };

  const restart = () => {
    window.clearInterval(intervalId);
    intervalId = window.setInterval(() => show(activeIndex + 1), 12000);
  };

  previous?.addEventListener("click", () => {
    show(activeIndex - 1);
    restart();
  });

  next?.addEventListener("click", () => {
    show(activeIndex + 1);
    restart();
  });

  dots.forEach((dot, index) => {
    dot.addEventListener("click", () => {
      show(index);
      restart();
    });
  });

  carousel.addEventListener("mouseenter", () => window.clearInterval(intervalId));
  carousel.addEventListener("mouseleave", restart);
  carousel.addEventListener("focusin", () => window.clearInterval(intervalId));
  carousel.addEventListener("focusout", (event) => {
    if (!carousel.contains(event.relatedTarget)) {
      restart();
    }
  });

  show(0);
  restart();
})();
