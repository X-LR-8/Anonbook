async function popupadd() {
   document.querySelector(".popup").style.display="flex";
}
async function popupsubmit(){
   var fileInput = document.getElementById('fileUpload');
   var file = fileInput.files[0];
   var filename=file.name;
   var textarea = document.getElementById('textarea').value;
   var encodedTextarea = encodeURIComponent(textarea);
   var url="/anonbook"+"/post"+`?text=${encodedTextarea}&imgurl=${filename}`;
   var response = await fetch(url, { method: "POST" });
   window.reload();
}
async function viewpost(id) {
   window.open("post.html")
   localStorage.setItem('dataid', id);
}

window.onload=async function (){
var url="/anonbook"+"/post";
var response = await fetch(url, { method: "GET" });
   var body = await response.text();
   var informationList = JSON.parse(body);
   createfieldsets(informationList);
}
async function createfieldsets(informationList) {
   console.log(informationList);
   const mainDiv = document.getElementById("themaindiv");
   informationList.reverse().forEach(item => {
      console.log(item.id);
      const fieldset = document.createElement("fieldset");
      fieldset.classList.add("listfieldset");

      const div = document.createElement("div");
      div.classList.add("verticallayoutforpost");

      const idLabel = document.createElement("label");
      idLabel.textContent = "N"+item.id+" "+item.date;
      div.appendChild(idLabel);

      const postTextLabel = document.createElement("label");
      postTextLabel.textContent = item.text;
      postTextLabel.classList.add("posttextlabel");
      div.appendChild(postTextLabel);

      const imgElement = document.createElement("img");
      imgElement.src = item.url;
      div.appendChild(imgElement);

      fieldset.appendChild(div);

      const viewPostButton = document.createElement("button");
      viewPostButton.type = "button";
      viewPostButton.classList.add("viewpostbutton");
      viewPostButton.textContent = "View Post";
      viewPostButton.onclick=function (){
         viewpost(item.id);
      }
      fieldset.appendChild(viewPostButton);

      mainDiv.appendChild(fieldset);
   });
}
