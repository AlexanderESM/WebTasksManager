// Получаем элемент canvas и его контекст для рисования
const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

// Устанавливаем размеры canvas на всю ширину и высоту окна
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

// Массив для хранения частиц
let particlesArray = [];

// Объект, хранящий координаты мыши и радиус зоны взаимодействия
let mouse = {
    x: null,
    y: null,
    radius: 150 // Радиус, в пределах которого частицы соединяются линиями
};

// Обработчик событий для отслеживания положения мыши
window.addEventListener('mousemove', function(event) {
    mouse.x = event.x;
    mouse.y = event.y;
});

// Класс для создания частиц
class Particle {
    constructor(x, y) {
        this.x = x; // Позиция X
        this.y = y; // Позиция Y
        this.size = Math.random() * 3 + 1; // Размер частицы (от 1 до 4)
        this.speedX = Math.random() * 3 - 1.5; // Скорость по X (-1.5 до 1.5)
        this.speedY = Math.random() * 3 - 1.5; // Скорость по Y (-1.5 до 1.5)
    }

    // Метод обновления позиции частицы
    update() {
        this.x += this.speedX;
        this.y += this.speedY;

        // Уменьшаем размер частицы со временем (имитируем исчезновение)
        if (this.size > 0.3) this.size -= 0.1;

        // Отскакивание от границ canvas
        if (this.x + this.size > canvas.width || this.x - this.size < 0) {
            this.speedX = -this.speedX;
        }
        if (this.y + this.size > canvas.height || this.y - this.size < 0) {
            this.speedY = -this.speedY;
        }
    }

    // Метод рисования частицы
    draw() {
        ctx.fillStyle = 'white';
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
        ctx.fill();
    }
}

// Функция инициализации частиц
function init() {
    particlesArray = [];
    for (let i = 0; i < 100; i++) {
        let x = Math.random() * canvas.width;
        let y = Math.random() * canvas.height;
        particlesArray.push(new Particle(x, y));
    }
    animate(); // Запускаем анимацию
}

// Функция обработки частиц (обновление, отрисовка и соединение линиями)
function handleParticles() {
    for (let i = 0; i < particlesArray.length; i++) {
        particlesArray[i].update();
        particlesArray[i].draw();

        // Проверяем расстояние между частицами и соединяем их линиями, если они близко
        for (let j = i; j < particlesArray.length; j++) {
            const dx = particlesArray[i].x - particlesArray[j].x;
            const dy = particlesArray[i].y - particlesArray[j].y;
            const distance = Math.sqrt(dx * dx + dy * dy);
            if (distance < mouse.radius) {
                ctx.beginPath();
                ctx.strokeStyle = 'white';
                ctx.lineWidth = particlesArray[i].size;
                ctx.moveTo(particlesArray[i].x, particlesArray[i].y);
                ctx.lineTo(particlesArray[j].x, particlesArray[j].y);
                ctx.stroke();
            }
        }
    }
}

// Функция анимации
function animate() {
    ctx.clearRect(0, 0, canvas.width, canvas.height); // Очищаем экран перед следующим кадром
    handleParticles(); // Обрабатываем частицы (обновляем, рисуем, соединяем)
    requestAnimationFrame(animate); // Рекурсивный вызов для плавной анимации
}

// Запуск программы
init();
animate();
