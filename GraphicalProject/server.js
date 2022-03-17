const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');


const app = express();

app.use(bodyParser.json());



const port = process.env.PORT || 3000;

app.use(express.static(__dirname + '/frontend'));

app.listen(port, () => {
  console.log(`Listening on port ${port}...`);  //Show on console
});

app.all('*', (req, res) => {
  res.set('Content-Type', 'text/html');
  res.sendFile(path.join(__dirname, '/frontend', 'home.html'));
});