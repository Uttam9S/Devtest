const express = require('express');
const app = express();
const port = 3001;

app.get('/', (req, res) => {
  res.send('YouTube Clone Backend API is running!');
});

app.listen(port, () => {
  console.log(`Server listening at http://localhost:${port}`);
});
