//Funciones Auxiliares
(() => {
    //Activar Links
    let pathname = window.location.pathname
    let navItem = Array.from(document.getElementsByName("nav-item"))
    navItem.forEach((item, i) => {

        if (pathname == item.firstElementChild.attributes[1].value) {
            if (item.classList.contains("active") == false) {
                item.classList.add("active")
            }
        }
        else if (i == 0) {
            if (pathname == "/") {
                if (item.classList.contains("active") == false) {
                    item.classList.add("active")
                }
            }
        }
        else if (item.classList.contains("active")) {
            item.classList.remove = "active"
        }
    })
})();
(() => {
    //Seleccionar Todos 
    let pivote = document.getElementById("bst")
    if (pivote != null) {
        pivote.addEventListener("click", () => {
            let checkboxs = Array.from(document.getElementsByName("cbst"))
            checkboxs.forEach(checkbox => checkbox.checked = true)
        })
    }
})();
(() => {
    //Resetiar el Formulario
    let pivote = document.getElementById("bbor")
    if (pivote != null) {
        pivote.addEventListener("click", () => {
            let form = document.getElementById("form")
            form.reset()
            let inputs = Array.from(form).filter(input => input.tagName == "INPUT" || input.tagName == "SELECT")
            inputs.forEach(input => {
                if (input.classList.contains("is-valid")) {
                    input.classList.remove("is-valid")
                    input.classList.add("is-invalid")
                }
            })
        })
    }
})();
(() => {
    //Boton Borrar 
    let pivote = document.getElementById("bobor")
    if (pivote != null) {
        pivote.addEventListener("click", () => {
            let checkboxs = Array.from(document.getElementsByName("cbst")).filter(checkbox => checkbox.checked == true)
            if (checkboxs.length > 0) {
                let formulario = `
                <form action="${window.location.pathname}/delete" method="POST" id="momentanio">
                    ${checkboxs.map(checkbox => `<input type="hidden" name="ids" value="${checkbox.id}">`)}
                </form>`
                document.getElementsByTagName("table")[0].insertAdjacentHTML("afterend", formulario)
                formulario = document.getElementById("momentanio")
                formulario.submit()
                formulario.remove()
            } else {
                Error("Seleccione Alguno");
            }
        })
    }
})();
(() => {
    //Filtrar
    let pivote = document.getElementById("filtar")
    if (pivote != null) {
        pivote.addEventListener("keypress", (e) => {
            if (e.key == "Enter") {
                let formulario = `
                <form action="${window.location.pathname}/buscar" method="POST" id="momentanio">
                    <input type="hidden" name="text" value="${e.target.value}">
                </form>`
                document.getElementsByTagName("table")[0].insertAdjacentHTML("afterend", formulario)
                formulario = document.getElementById("momentanio")
                formulario.submit()
                formulario.remove()
            }
        })
    }
})();
(() => {
    //Canselar Filtrado
    let pivote = document.getElementById("bfiltar")
    if (pivote != null) {
        pivote.addEventListener("click", (e) => {
            document.getElementById("filtar").value = ""
            let formulario = `
                <form action="${window.location.pathname}/buscar" method="POST" id="momentanio">
                    <input type="hidden" name="text" value="">
                </form>`
            document.getElementsByTagName("table")[0].insertAdjacentHTML("afterend", formulario)
            formulario = document.getElementById("momentanio")
            formulario.submit()
            formulario.remove()
        })
    }
})();
//Balidaciones y Metodos auxiliares para esto
(() => {
    //Balidador de la salida del Formulario
    let forms = Array.from(document.getElementsByTagName("form"))
    if (forms != null) {
        forms.forEach(form => {
            form.addEventListener("submit", (e) => {
                let balido = true
                let inputs = Array.from(form).filter(input => input.tagName == "INPUT" || input.tagName == "SELECT")
                for (const input of inputs) {
                    if (input.classList.contains("is-invalid")) {
                        balido = false;
                        break;
                    }
                }
                if (!balido) {
                    e.preventDefault()
                    Error("Datos Incorrectos")
                }
            })
        })
    }
})();
(() => {
    //Balidar Nombre
    let pivote = document.getElementById("nombre")
    if (pivote != null) {
        pivote.addEventListener("input", (e) => {
            let sugerencias = Array.from(document.getElementsByName("sugerenciaId")), balido = true
            if (sugerencias.length != 0) {
                balido = false
                document.getElementById("userId").value = ""
                sugerencias.forEach(s => {
                    if (s.innerHTML == e.target.value) {
                        balido = true
                        document.getElementById("userId").value = s.id
                        s.classList.remove("d-none")
                    } else if (s.innerText.includes(e.target.value)) {
                        s.classList.remove("d-none")
                    } else {
                        s.classList.add("d-none")
                    }
                })
            } else {
                let valor = e.target.value.toString()
                if (balido) {
                    balido = Boolean(valor.length >= 3)
                }
                if (balido) {
                    balido = !/\d/.test(valor)
                }
                if (balido) {
                    balido = !/([0-9]|[A-Z][A-Z]|[a-z][A-Z]|\s[a-z])/.test(valor)
                }
                if (balido) {
                    balido = /([A-Z][a-z]+\s[A-Z]|[A-Z][a-z]+)/.test(valor)
                }
                if (balido) {
                    valor = valor.match(/\W/g);
                    if (valor != null) {
                        valor = valor.filter(v => (v != " "))
                        if (valor.length != 0) {
                            balido = false;
                        }
                    }
                }
            }
            //Cambo de clase Inbalida a clase Valida
            if (balido) {
                e.target.classList.remove("is-invalid")
                e.target.classList.add("is-valid")
            } else {
                e.target.classList.remove("is-valid")
                e.target.classList.add("is-invalid")
            }
        })
    }
})();
(() => {
    //Balidar solapin
    let pivote = document.getElementById("solapin")
    if (pivote != null) {
        pivote.addEventListener("input", (e) => {
            let valor = e.target.value.toString(), balido = true
            if (balido) {
                balido = Boolean(valor.length == 8)
            }
            if (balido) {
                balido = /[A-Z]/.test(valor[0])
            }
            if (balido) {
                balido = !/\D/g.test(valor.substr(1, valor.length))
            }
            //Cambo de clase Inbalida a clase Valida
            if (balido) {
                e.target.classList.remove("is-invalid")
                e.target.classList.add("is-valid")
            } else {
                e.target.classList.remove("is-valid")
                e.target.classList.add("is-invalid")
            }
        })
    }
})();
(() => {
    //Balidar Usuario
    let pivote = document.getElementById("usuario")
    if (pivote != null) {
        pivote.addEventListener("input", (e) => {
            let valor = e.target.value.toString(), balido = true
            if (balido) {
                balido = Boolean(valor.length >= 4)
            }
            if (balido) {
                balido = /[a-z]/.test(valor)
            }
            if (balido) {
                balido = !/\W/g.test(valor)
            }
            //Cambo de clase Inbalida a clase Valida
            if (balido) {
                e.target.classList.remove("is-invalid")
                e.target.classList.add("is-valid")
            } else {
                e.target.classList.remove("is-valid")
                e.target.classList.add("is-invalid")
            }
        })
    }
})();
(() => {
    //Balidar Contraseña
    let pivote = document.getElementById("contraseña")
    if (pivote != null) {
        pivote.addEventListener("input", (e) => {
            let valor = e.target.value.toString(), balido = true
            if (balido) {
                balido = Boolean(valor.length >= 4)
            }
            if (balido) {
                balido = !/\W/.test(valor)
            }
            //Cambo de clase Inbalida a clase Valida
            if (balido) {
                e.target.classList.remove("is-invalid")
                e.target.classList.add("is-valid")
            } else {
                e.target.classList.remove("is-valid")
                e.target.classList.add("is-invalid")
            }
        })
    }
})();
(() => {
    //Balidar Rol
    let pivote = document.getElementById("rol")
    if (pivote != null) {
        pivote.addEventListener("change", (e) => {
            let valor = e.target.value.toString(), balido = true
            switch (valor) {
                case "Administrador":
                case "DrRecidence":
                case "VicDecExtencion":
                case "Intructura":
                case "Profesor":
                case "Estudiante":
                    balido = true;
                    break;
                default:
                    balido = false;
                    break;
            }
            //Cambo de clase Inbalida a clase Valida
            if (balido) {
                e.target.classList.remove("is-invalid")
                e.target.classList.add("is-valid")
            } else {
                e.target.classList.remove("is-valid")
                e.target.classList.add("is-invalid")
            }
        })
    }
})();
(() => {
    //Balidar Apartamento
    let pivote = document.getElementById("apartamento")
    if (pivote != null) {
        pivote.addEventListener("input", (e) => {
            let valor = e.target.value.toString(), balido = true
            if (balido) {
                balido = Boolean(valor.length <= 6 && valor.length >= 4)
            }
            if (balido) {
                balido = !/\D/.test(valor)
            }
            //Cambo de clase Inbalida a clase Valida
            if (balido) {
                e.target.classList.remove("is-invalid")
                e.target.classList.add("is-valid")
            } else {
                e.target.classList.remove("is-valid")
                e.target.classList.add("is-invalid")
            }
        })
    }
})();
(() => {
    //Balidar Grupo
    let pivote = document.getElementById("grupo")
    if (pivote != null) {
        pivote.addEventListener("input", (e) => {
            let valor = e.target.value.toString(), balido = true
            if (balido) {
                balido = Boolean(valor.length == 4)
            }
            if (balido) {
                balido = !/\D/.test(valor)
            }
            //Cambo de clase Inbalida a clase Valida
            if (balido) {
                e.target.classList.remove("is-invalid")
                e.target.classList.add("is-valid")
            } else {
                e.target.classList.remove("is-valid")
                e.target.classList.add("is-invalid")
            }
        })
    }
})();
//Funciones para Mostar Error o Notificar
function Mensaje(texto = "") {
    let contenedor = document.getElementById("contenedorDeNotificaciones")
    let referencia = document.getElementById("referencia")
    let alerta = `<div class="alert alert-success " id="mensaje" role="alert">
                    Mensaje : ${texto}
                </div>`
    referencia.insertAdjacentHTML("afterend", alerta)
    setTimeout(() => {
        contenedor.childNodes[2].remove()
    }, 3000)
}
function Error(texto = "") {
    let contenedor = document.getElementById("contenedorDeNotificaciones")
    let referencia = document.getElementById("referencia")
    let alerta = `<div class="alert alert-danger " id="mensaje" role="alert">
                    Error : ${texto}
                </div>`
    referencia.insertAdjacentHTML("afterend", alerta)
    setTimeout(() => {
        contenedor.childNodes[2].remove()
    }, 3000)
}
//Funcion para las autosugerencias
(() => {
    let pivotes = Array.from(document.getElementsByName("sugerenciaId"));
    if (pivotes.length != 0) {
        pivotes.forEach(pivote => {
            pivote.addEventListener("click", () => {
                document.getElementById("userId").value = pivote.id
                let nombre = document.getElementById("nombre")
                nombre.value = pivote.innerText
                nombre.classList.remove("is-invalid")
                nombre.classList.add("is-valid")
            })
        })
    }
})();