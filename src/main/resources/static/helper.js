// Actibar Links
(() => {
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
})()