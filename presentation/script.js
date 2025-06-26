// Subtle random flicker effect for the title
const title = document.querySelector('.title');
function randomFlicker() {
  if (!title) return;
  const flicker = Math.random() * 0.2 + 0.8;
  title.style.opacity = flicker;
  setTimeout(() => {
    title.style.opacity = 1;
  }, 120 + Math.random() * 80);
}
setInterval(randomFlicker, 1800);

// Horizontal navigation logic
const slides = document.querySelector('.slides');
const navLeft = document.getElementById('navLeft');
const navRight = document.getElementById('navRight');
let currentSlide = 0;

const goalEls = [
  document.getElementById('goal1'),
  document.getElementById('goal2'),
  document.getElementById('goal3'),
  document.getElementById('goal4')
];

function resetGoals() {
  goalEls.forEach(el => {
    el.classList.remove('visible');
    el.textContent = '';
  });
}

function goToSlide(idx) {
  currentSlide = idx;
  slides.style.transform = `translateX(-${idx * 100}vw)`;
  // Show/hide arrows for seven slides
  if (navLeft) navLeft.style.display = idx === 0 ? 'none' : 'flex';
  if (navRight) navRight.style.display = idx === 6 ? 'none' : 'flex';
  if (idx === 1) {
    resetGoals();
    setTimeout(startGoalsAnimation, 400);
  } else {
    resetGoals();
  }
  if (idx === 2) {
    resetComponents();
    setTimeout(startComponentsAnimation, 400);
  } else {
    resetComponents();
  }
}
if (navLeft) navLeft.addEventListener('click', () => goToSlide(currentSlide - 1));
if (navRight) navRight.addEventListener('click', () => goToSlide(currentSlide + 1));

// Keyboard navigation
window.addEventListener('keydown', (e) => {
  if (e.key === 'ArrowRight' && currentSlide < 2) {
    goToSlide(currentSlide + 1);
  } else if (e.key === 'ArrowLeft' && currentSlide > 0) {
    goToSlide(currentSlide - 1);
  }
});

// Terminal typewriter effect for project goals
const goals = [
  "Convertir des chaînes entre plusieurs bases (hexadécimal, octal, décimal, binaire, texte)",
  "Implémenter la logique de conversion manuellement (sans fonctions intégrées)",
  "Assurer la réversibilité et l'intégration du chiffrement/déchiffrement (chiffre de César)",
  "Fournir une interface en ligne de commande avec validation robuste"
];

function typeGoal(element, text, delay = 30, callback) {
  let i = 0;
  element.textContent = '';
  function type() {
    if (i < text.length) {
      element.textContent += text.charAt(i);
      i++;
      setTimeout(type, delay + Math.random() * 30);
    } else if (callback) {
      callback();
    }
  }
  type();
}

function startGoalsAnimation() {
  let idx = 0;
  function showNextGoal() {
    if (idx < goals.length) {
      const el = goalEls[idx];
      el.classList.add('visible');
      typeGoal(el, goals[idx], 22, () => {
        idx++;
        setTimeout(showNextGoal, 600);
      });
    }
  }
  showNextGoal();
}

// Binary rain animation for goals slide and diagram slide
function binaryRain() {
  const backgrounds = document.querySelectorAll('.goals-background');
  backgrounds.forEach(goalsBg => {
    let canvas = document.createElement('canvas');
    canvas.className = 'binary-canvas';
    goalsBg.appendChild(canvas);
    let ctx = canvas.getContext('2d');
    let w, h, cols, yPos;
    function resize() {
      w = goalsBg.offsetWidth;
      h = goalsBg.offsetHeight;
      canvas.width = w;
      canvas.height = h;
      cols = Math.floor(w / 18);
      yPos = Array(cols).fill(0);
    }
    window.addEventListener('resize', resize);
    resize();
    function draw() {
      ctx.fillStyle = 'rgba(0,0,0,0.15)';
      ctx.fillRect(0, 0, w, h);
      ctx.font = '18px Share Tech Mono, monospace';
      ctx.fillStyle = '#39ff14';
      for (let i = 0; i < cols; i++) {
        const text = Math.random() > 0.5 ? '0' : '1';
        ctx.fillText(text, i * 18, yPos[i] * 18);
        if (yPos[i] * 18 > h && Math.random() > 0.975) {
          yPos[i] = 0;
        } else {
          yPos[i]++;
        }
      }
      requestAnimationFrame(draw);
    }
    draw();
  });
}
window.addEventListener('DOMContentLoaded', binaryRain);

// On load, show first slide
window.addEventListener('DOMContentLoaded', () => {
  goToSlide(0);
  // Set glitch code data-text for glitch effect
  const glitchCode = document.querySelector('.glitch-code');
  if (glitchCode) {
    glitchCode.setAttribute('data-text', glitchCode.textContent);
  }
});

// Diagram button logic
const diagramBtn = document.getElementById('diagramBtn');
if (diagramBtn) {
  diagramBtn.addEventListener('click', () => {
    window.open('../documentation/mermaid-diagram-2025-06-26-135431.png', '_blank');
  });
}

function typeComponent(element, text, delay = 22, callback) {
  let i = 0;
  element.textContent = '';
  function type() {
    if (i < text.length) {
      element.textContent += text.charAt(i);
      i++;
      setTimeout(type, delay + Math.random() * 20);
    } else if (callback) {
      callback();
    }
  }
  type();
}

function resetComponents() {
  for (let i = 1; i <= 4; i++) {
    const comp = document.getElementById('component' + i);
    if (comp) {
      comp.classList.remove('visible');
      const desc = comp.querySelector('.component-desc-text');
      if (desc) desc.textContent = desc.getAttribute('data-original') || desc.textContent;
    }
  }
}

function startComponentsAnimation() {
  let idx = 1;
  function showNextComponent() {
    if (idx <= 4) {
      const comp = document.getElementById('component' + idx);
      if (comp) {
        comp.classList.add('visible');
        const desc = comp.querySelector('.component-desc-text');
        if (desc) {
          if (!desc.getAttribute('data-original')) desc.setAttribute('data-original', desc.textContent);
          typeComponent(desc, desc.getAttribute('data-original'), 18, () => {
            idx++;
            setTimeout(showNextComponent, 500);
          });
        } else {
          idx++;
          setTimeout(showNextComponent, 500);
        }
      }
    }
  }
  showNextComponent();
} 