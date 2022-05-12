var start = Date.now();

function handleItemClick(product) {

  var delta = Date.now() - start;
  console.log("[ProductList: " + delta + "]");

  var item = product.slice();

  localStorage.setItem("productName", item);
  changePage("/item.html");
}

function changePage(pageName) {
  window.location.href = pageName;
}
