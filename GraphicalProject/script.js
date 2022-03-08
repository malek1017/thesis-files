function handleItemClick(product) {

  var item = product.slice();

  localStorage.setItem("productName", item);
  changePage("/item.html");
}

function changePage(pageName) {
  window.location.href = pageName;
}