function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
function main(){
    sort()
}
function sort() {
    let radio = document.getElementById("supID");
    let prod = document.getElementById("products");
    radio.addEventListener("click",()=>{
        console.log(prod.getAttribute("data-prod"));
    })
}
main();