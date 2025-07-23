document.addEventListener("DOMContentLoaded", () => {
    cargarLibros();

    const form = document.getElementById("formLibro");
    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const libro = {
            titulo: document.getElementById("titulo").value,
            autor: document.getElementById("autor").value,
            categoria: document.getElementById("categoria").value
        };

        await fetch("/api/libros", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(libro)
        });

        form.reset();
        cargarLibros();
    });
});

function cargarLibros() {
    fetch("/api/libros")
        .then(res => res.json())
        .then(libros => {
            const tabla = document.getElementById("tablaLibros");
            tabla.innerHTML = "";
            libros.forEach(libro => {
                const fila = `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        <td>${libro.categoria}</td>
                    </tr>`;
                tabla.innerHTML += fila;
            });
        });
}