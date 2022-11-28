const sizeCheckbox = document.querySelectorAll("input[name='category']");
const supplierCheckBox = document.querySelectorAll("input[name='supplier']");
const productsContainer = document.querySelector("#products");

let categoryId;
let supplierId;

sizeCheckbox.forEach(checks => {
    checks.addEventListener("change", async (e) => {
        if (e.target.checked) {
            categoryId = e.target.value;
            productsContainer.textContent = "";
            const response = await fetchFilteredProducts(categoryId, supplierId);
            createCards(response);
        } else {
            productsContainer.textContent = "";
            categoryId = 0;
            const response = await fetchFilteredProducts(categoryId, supplierId);
            createCards(response);
        }
    });
})

supplierCheckBox.forEach(checks => {
    checks.addEventListener("change", async (e) => {
        if (e.target.checked) {
            supplierId = e.target.value;
            productsContainer.textContent = "";
            const response = await fetchFilteredProducts(categoryId, supplierId);
            createCards(response);
        } else {
            productsContainer.textContent = "";
            supplierId = 0;
            const response = await fetchFilteredProducts(categoryId, supplierId);
            createCards(response);
            console.log(await response);
        }
    });
})

async function fetchFilteredProducts(categoryId=0, supplierId=0) {
    const response = await fetch(`/api/products?categoryId=${categoryId}&supplierId=${supplierId}`);
    return response.json();
}

function createCards(response) {
    for (const resp of response) {
        const card = document.createElement("div");
        card.classList.add("col");
        if (response.length > 2) {
            card.classList.add("col-sm-12");
            card.classList.add("col-md-6");
            card.classList.add("col-lg-4");
        }
        else if (response.length === 2) {
            card.classList.add("col-sm-6");
        }
        else {
            card.classList.add("col-sm-12");
        }

        card.innerHTML =
            "            <div class=\"card\">\n" +
            `                <img class=\"\" src=\"static/img/product_${resp.id}.jpg"\n` +
            "                     alt=\"\"/>\n" +
            "                <div class=\"card-header\">\n" +
            `                    <h4 class=\"card-title\">${resp.name}</h4>\n` +
            `                    <p class=\"card-text\">${resp.description} </p>\n` +
            "                </div>\n" +
            "                <div class=\"card-body\">\n" +
            "                    <div class=\"card-text\">\n" +
            `                        <p class=\"lead\">${resp.defaultPrice} USD</p>\n` +
            "                    </div>\n" +
            "                    <div class=\"card-text\">\n" +
            "                        <a class=\"btn btn-success\" href=\"#\">Add to cart</a>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n"
        productsContainer.appendChild(card);
    }
}