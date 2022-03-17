const btn = document.getElementById("btn");

btn.addEventListener("click", () => registrar());


const registrar = async () => {


    event.preventDefault();
    const dni = document.getElementById("dni");
    const nombre2 = document.getElementById("nombre");
    const apelpat = document.getElementById("apelpat");
    const apelmat = document.getElementById("apelmat");
    const telefono = document.getElementById("telefono");
    const celular = document.getElementById("celular");
    const correo = document.getElementById("correo");



    // Consegir el nombre del DNI
    const options = {
        method: 'POST',
        headers: {Accept: 'application/json', 'Content-Type': 'application/json'},
        body: JSON.stringify({token: 'nzwE45RqkPFR2GN8XtXxAIK63pyvOHFRF9TsGI8BYN6PINiLJX0dI51zRXQ4', dni: dni.value})
      };

      const res = await fetch('https://api.migo.pe/api/v1/dni', options)
        .then(response => response.json())
        .then(response => response)
         .catch(err => console.error(err));

    console.log(res);
    console.log(res.success);

    if (res.success){

        var nombre = res.nombre;

        // Registrar en la BD

        const persona = {
            "dni": dni.value,
            "nombre": nombre2,
            "apelpat": apelpat.value,
            "apelmat": apelmat.value,
            "telefono":telefono.value,
            "celular":celular.value,
            "correo": correo.value
            }
            await fetch('localhost:5432/Registro', {
                        method: 'POST',
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(persona)
                    })
        }
    else{
        alert("¡Error en el DNI!")
    }
}