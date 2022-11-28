const shippingBillingCheckbox = document.querySelector("#shippingIsSameAsBilling");
const shippingForm = document.querySelector(".shipping-form");

shippingBillingCheckbox.addEventListener("change", e => {
    if (e.target.checked) {
        shippingForm.setAttribute("disabled", "disabled");
    } else {
        shippingForm.removeAttribute("disabled");
    }
})