document.addEventListener('click', function (e) {
        const boton = e.target.closest('.js-editar, .js-eliminar');
        if (!boton) return;
 
        const modalSelector = boton.getAttribute('data-bs-target');
        const modal = document.querySelector(modalSelector);
        if (!modal) return;
 
        const form = modal.querySelector('form');
        if (!form) return;
 
        for (const campo in boton.dataset) {
            const input = form.querySelector('[name="' + campo + '"]');
            if (input) {
                input.value = boton.dataset[campo];
            }
        }
 
        const nombreSpan = modal.querySelector('.js-nombre-registro');
        if (nombreSpan && boton.dataset.nombreMostrar) {
            nombreSpan.textContent = boton.dataset.nombreMostrar;
        }
    });