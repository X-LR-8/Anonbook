window.onload=async function (){
    const getid = localStorage.getItem('dataid');
    var url="/anonbook"+"/comment";
    var response = await fetch(url, { method: "GET" });
    var body = await response.text();
    var informationList = JSON.parse(body);
    var id;
    var date;
    var text;
    var imgurl;
    var comments;
    informationList.forEach(item =>{
       if(item.id==getid){
           id=item.id;
           date=item.date;
           text=item.text;
           imgurl=item.url;
           comments=item.comments;
       }
    });
    const iddate=document.getElementById("id-date");
    const posttext=document.getElementById("text");
    const img=document.getElementById("imgurl");
    const comment=document.getElementById("comments");
    iddate.textContent = "N"+id+" "+date;
    posttext.textContent = text;
    img.src = imgurl;

    var message=comments.split('\n');
    var messagelist="";
    var count=0;
    message.forEach(item=>{
        let number="-["+count+"] ";
        if(item!=""){
            var commentexp=number+item;
            messagelist+='<li style="list-style-type: none; margin-top: 8px; margin-bottom: 8px">'+commentexp+'</li>';
        }
        count++;
    });
    comment.innerHTML = `<ul style="margin: 0; padding: 0;">${messagelist}</ul>`;
}
async function comment(){
    var textarea=document.getElementById('textarea').value;
    var encodedtextarea=encodeURIComponent(textarea);
    const getid = localStorage.getItem('dataid');
    var url="/anonbook"+"/comment"+`?textarea=${encodedtextarea}&id=${getid}`;
    var response = await fetch(url, { method: "POST" });
    location.reload();
}