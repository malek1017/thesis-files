var oldStr = localStorage.getItem("productCart");

function changePage(pageName) {
  window.location.href = pageName;
}

window.addEventListener('load', (event) => {
  loadCart();
});


function loadCart(){
  var oldStr = localStorage.getItem("productCart");

  console.log(oldStr.length);

  var generatedHTML = "";

  var arr = oldStr.split(',');

  for(var i=0; i < arr.length; i++){
  
    if(arr[i].length > 2){
      generatedHTML = generatedHTML.concat('<p>' + arr[i] +'</p>');
    }
  }

  document.getElementById("cartList").innerHTML = generatedHTML;
}

function handleSubmit(){
  
  document.getElementById("submitText").innerHTML = "Thank you for ordering";

}