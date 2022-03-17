var description = [
  ['apple', 'This is an apple'],
  ['pear', 'This is a pear']
];

window.addEventListener('load', (event) => {
  var product = localStorage.getItem("productName")
  setProduct(product);
});


function setProduct(product) {
  var descr;

  for (var i = 0; i < description.length; i++) {

    if (description[i][0] = 'product') {
      descr = description[i][1];
    }
  }

  var generatedHTML = '<img height="400px" width="400px" style="float: left; margin-right: 20px;" src="images/' + product + '.jpg"><div id="itemText">' + descr + '</div><button onclick="addToCart(\'' + product + '\')">Add to Cart</button>';

  document.getElementById("itemContent").innerHTML = generatedHTML;
}

function changePage(pageName) {
  window.location.href = pageName;
}

function addToCart(product) {

  var oldStr = localStorage.getItem("productCart");

  var i = product.slice();

  console.log(i);


  if ((oldStr === null) || (oldStr.length = 0)) {
    newStr = i;
  } else {
    newStr = oldStr.concat(",", i);
  }

  localStorage.setItem("productCart", newStr);

  handleSubmitItem();
}

function handleSubmitItem() {
  document.getElementById("submitItem").innerHTML = "Added to the cart.";
}