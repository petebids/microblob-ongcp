FROM node:14

COPY package*.json ./

RUN npm install


COPY App.js app.js

CMD ["node", "app.js"]