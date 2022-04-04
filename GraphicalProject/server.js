const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
// var os = require('os');
var os = require('os-utils');


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




// console.log(os.cpus());
// console.log(os.totalmem());
// console.log(os.freemem())

// var cpus = os.cpus();

// for (var i = 0, len = cpus.length; i < len; i++) {
//   console.log("CPU %s:", i);
//   var cpu = cpus[i], total = 0;

//   for (var type in cpu.times) {
//     total += cpu.times[type];
//   }

//   for (type in cpu.times) {
//     console.log("\t", type, Math.round(100 * cpu.times[type] / total));
//   }
// }



console.log('\n');
console.log('OS Utils');
console.log('\n');

console.log('Platform: ' + os.platform());
console.log('CPUs: ' + os.cpuCount());
console.log('\n');

console.log('System Uptime (s): ' + os.sysUptime());
console.log('Process Uptime (s): ' + os.processUptime());
console.log('\n');

console.log('Free Memory (Kb): ' + os.freemem());
console.log('total Memory (Kb): ' + os.totalmem());
console.log('Free Memory (%): ' + os.freememPercentage());
console.log('\n');

console.log('Load Usage (%): ' + os.loadavg());
console.log('Load Usage 1 (%): ' + os.loadavg(1));
console.log('Load Usage 5 (%): ' + os.loadavg(5));
console.log('Load Usage 15 (%): ' + os.loadavg(15));
console.log('\n');

os.cpuUsage(function (v) {
  console.log('CPU Usage (%): ' + v);
});

os.cpuFree(function (v) {
  console.log('CPU Free (%): ' + v);
});
