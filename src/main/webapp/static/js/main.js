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
    let radio_buttons = document.getElementsByClassName("radio-button");
    let product = document.getElementsByClassName("prod");
    for(let radio of radio_buttons){
        radio.addEventListener("click",(e)=>{
            for(let one_prod of product) {
                let name = one_prod.getAttribute("data-name");
                let id = one_prod.getAttribute("data-id");
                let description = one_prod.getAttribute("data-description");
                let price = one_prod.getAttribute("data-price");
                if (radio.getAttribute("data-sup-name") === one_prod.getAttribute("data-supp-name")) {
                    console.log("OK");
                    one_prod.innerHTML = `<img class="" src="'/static/img/product_' + ${id} + '.jpg'" alt=""/>
                                               <div class="card-header">
                                                   <h4 class="card-title" th:text="${name}">${name}</h4>
                                                  <p class="card-text" th:text="${description}">${description} </p>
                                              </div>
                                             <div class="card-body">
                                                  <div class="card-text">
                                                       <p class="lead" th:text="${id}">${price}</p>
                                                   </div>
                                                    <div class="card-text">
                                                        <a class="btn btn-success" th:href="${'?id='+id}">Add to cart</a>
                                                    </div>
                                                </div>`;
                }
                else{
                    one_prod.innerHTML="";
                }
            }
        })
    }
}
main();