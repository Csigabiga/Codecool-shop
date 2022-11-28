async function changeItemQuantityInCart(productId, productQuantity){
    const response = await fetch(`/api/cart/change?prod-id=${productId}&prod-quantity=${productQuantity}`)
}
async function postAddCartRequest(productId, productQuantity) {
    const response = await fetch(`/api/cart/add?prod-id=${productId}&prod-quantity=${productQuantity}`);
}
async function postRemoveCartRequest(productId) {
    const response = await fetch(`/api/cart/remove?prod-id=${productId}`);
}
async function getCardItems(){
    const response = await fetch("/api/cart/get");
    return response.json();
}

 function addToCartEventListener() {
    let cartButtons = document.querySelectorAll(".add-to-cart-button");
    for (let i = 0; i < cartButtons.length; i++) {
        cartButtons[i].addEventListener('click', function () {
            let productQuantity = document.querySelector(`#quant-input-${cartButtons[i].id}`).value;
            postAddCartRequest(cartButtons[i].id, productQuantity).then();
            updateCartItemCounter();
        });
    }
}
function addCartButtonEventListener(){
    let navbarCartButton =  document.querySelector("#navbar-cart-button");
    let cartModal = document.querySelector("#cart-modal");
    let myModal = new bootstrap.Modal(document.getElementById('cart-modal'));
    navbarCartButton.addEventListener('click',  function () {
        updateCartItemCounter();
        displayCartItems();
        myModal.show();
    })
}
async function updateCartItemCounter() {
    let data = await getCardItems();
    let totalItem = 0;
    data.forEach(product => totalItem += product.quantity);
    let itemCounter = document.querySelector("#cart-item-count");
    itemCounter.dataset.count = totalItem;
}

async function displayCartItems() {
    let emptyCartText = "<p>Your cart is empty! Please go buy a ship :)</p>";
    let cartModalBody = document.querySelector(".modal-body");
    let data = await getCardItems();
    let subtotal = document.querySelector("#subtotal-number");
    let subtotalNumber = 0;
    if (cartModalBody.children[0]){
        cartModalBody.innerHTML= "";
    }
    if (data[0] == null){
        cartModalBody.innerHTML = emptyCartText;
    }
    for(const item of data){
        subtotalNumber += item.totalPrice;
        let itemCard = document.createElement('div');
        itemCard.classList.add('card', 'flex-row');
        itemCard.dataset.productid = `${item.product.id}`;
        let itemImage = document.createElement('img');
        itemImage.classList.add('card-img-left', 'example-card-img-responsive');
        itemImage.src = `/static/img/product_${item.product.id}.jpg`;
        itemCard.append(itemImage);
        let cardBody = document.createElement('div');
        cardBody.classList.add("card-body");
        itemCard.append(cardBody);
        let itemName = document.createElement('h5');
        itemName.classList.add('card-title', 'bold' , 'h5', 'h4-sm');
        itemName.innerText = `${item.product.name}`;

        let itemQuantityContainer = document.createElement('div');
        let quantityPlus = document.createElement('div');
        quantityPlus.id = "quantity-plus-button";
        let quantityNumber = document.createElement('h5');
        quantityNumber.id = "quantity-number-text";

        let quantityMinus = document.createElement('div');
        quantityMinus.id = "quantity-minus-button";
        quantityNumber.innerText = `${item.quantity}`;
        quantityMinus.innerHTML = `<i class="fa-solid fa-square-minus clickable"></i>`;
        quantityPlus.innerHTML = `<i class="fa-solid fa-square-plus clickable"></i>`;
        itemQuantityContainer.append(quantityPlus, quantityNumber, quantityMinus);
        cardBody.append(itemQuantityContainer);
        quantityPlus.addEventListener('click', function (){
            changeItemQuantityInCart(item.product.id, item.quantity+1);
            displayCartItems();
            updateCartItemCounter();
        })
        quantityMinus.addEventListener('click', function (){
            changeItemQuantityInCart(item.product.id, item.quantity-1);
            displayCartItems();
            updateCartItemCounter();
        })

        itemCard.append(itemName);
        let itemPrice = document.createElement('h3');
        itemPrice.classList.add('card-title', 'h5', 'text-end', 'font-weight-bold');
        itemPrice.innerText = `Total : ${item.totalPrice}$`;
        itemCard.append(itemPrice);
        cartModalBody.append(itemCard);

        let removeItem = document.createElement('div');
        removeItem.innerHTML= `<i class="fa-solid fa-trash clickable"></i>`;
        removeItem.dataset.productid = `${item.product.id}`;
        removeItem.addEventListener('click', function (){
            postRemoveCartRequest(item.product.id);
            displayCartItems();
            updateCartItemCounter();
        })
        itemCard.append(removeItem);
    }
    subtotal.textContent = `Subtotal ${subtotalNumber}$`;
}

addToCartEventListener();
addCartButtonEventListener();
updateCartItemCounter();
