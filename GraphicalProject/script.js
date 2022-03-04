description = 'This is an apple';


function handleItemClick(item) {
  console.log("clicked: " + item);

  changePage("/item.html");

  setItem(item);
}

function changePage(pageName) {
  window.location.href = pageName;
}

function setItem(item) {

  var generatedHTML = '<img height="400px" width="400px" style="float: left; margin-right: 20px;" src="images/' + item + '.png"><div id="itemText">' + description + '</div><button>Add to Cart</button>';
  document.getElementById("content").innerHTML = generatedHTML;
}