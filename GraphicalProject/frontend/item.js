var description = [
  ['apple', 'Apples are not native to North America. They originated in Kazakhstan, in central Asia east of the Caspian Sea. The capital of Kazakhstan, Alma Ata, means “full of apples.” By 1500 BC apple seeds had been carried throughout Europe. The Greeks, Etruscans, and Romans cultivated apples.'],
  ['banana', 'Bananas were originally found in South East Asia, mainly in India. They were brought west by Arab conquerors in 327 B.C. and moved from Asia Minor to Africa and finally carried to the New World by the first explorers and missionaries to the Caribbean.'],
  ['strawberry', 'The first garden strawberry was grown in Brittany, France, during the late 18th century. Prior to this, wild strawberries and cultivated selections from wild strawberry species were the common source of the fruit. The strawberry fruit was mentioned in ancient Roman literature in reference to its medicinal use.'],
  ['orange', 'The orange originated in a region encompassing Southern China, Northeast India, and Myanmar, and the earliest mention of the sweet orange was in Chinese literature in 314 BC. As of 1987, orange trees were found to be the most cultivated fruit tree in the world.'],
  ['pineapple', 'Pineapple is believed to have originated in the Brazilian rainforests. Pineapples were harvested by the native tribes and spread throughout South and Central America. When Christopher Columbus landed in the new world in 1493, the Spaniards named the fruit “piña” due to its resemblance to a pinecone.'],
  ['kiwi', 'The original fruit is from the Far East, having been grown in what is now modern-day China for many centuries. It was only at the turn of the 20th Century, in 1904, that it arrived on New Zealand shores, when New Zealand school principal Isabel Fraser brought some kiwifruit seeds back from her travels.'],
  ['grape', 'Archeological evidence suggests humans began growing grapes as early as 6500 B.C. during the Neolithic era. By 4000 B.C., grape growing extended from Transcaucasia to Asia Minor and through the Nile Delta of Egypt.'],
  ['blackberry', 'The blackberry is an edible fruit produced by many species in the genus Rubus in the family Rosaceae, hybrids among these species within the subgenus Rubus, and hybrids between the subgenera Rubus and Idaeobatus. The taxonomy of blackberries has historically been confused because of hybridization and apomixis, so that species have often been grouped together and called species aggregates.'],
  ['mango', 'Mangoes originated in India over 4,000 years ago and are considered a sacred fruit. Mangoes spread gradually throughout Asia and then to the rest of the world. Due to a mangos large center seed, the fruit relied on humans to transport them across the world.'],
  ['peach', 'The peach probably originated in China and then spread westward through Asia to the Mediterranean countries and later to other parts of Europe. The Spanish explorers took the peach to the New World, and as early as 1600 the fruit was found in Mexico.'],
];

var start = Date.now();


window.addEventListener('load', (event) => {
  var product = localStorage.getItem("productName")
  setProduct(product);
});


function setProduct(product) {
  var descr;

  for (var i = 0; i < description.length; i++) {

    if (description[i][0] == product) {
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

  var delta = Date.now() - start;
  console.log("[ProductDesc: " + delta + "]");

  var oldStr = localStorage.getItem("productCart");

  var i = product.slice();

  console.log(i);


  if ((oldStr === null) || (oldStr.length = 0)) {
    newStr = i;
  } else {
    newStr = oldStr.concat(",", i);
  }

  // newStr = "";

  localStorage.setItem("productCart", newStr);

  handleSubmitItem();
}

function handleSubmitItem() {
  document.getElementById("submitItem").innerHTML = "Added to the cart.";
}