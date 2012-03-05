// CSSslide.js v1.0 Copyright (C)thira ( http://thira.plavox.info/ )
// Licensed under Creative Commons Attribution 2.1 Japan ( http://creativecommons.org/licenses/by/2.1/jp/ )
// Licensed under MIT License (http://www.opensource.org/licenses/mit-license.php)

//
// How to Use
// ← / → Prev / Next 
// < / > Smaller / Larger

var CSSslide={
 slide:[], //スライドが入る配列
 nowPage:0, //現在のページ
 enableUsagePage:0, //使い方のページを表示する
 enableRestartPage:0, //最初に戻るページを表示する
 enableFontResize:1, //起動時にフォントサイズを自動調整する
 fontSizeRatio:100, //フォントの拡大率(%)
 fontSizeRatioDefault:100, //元のフォントの拡大率(%)
 enableLoop:0, //ループ機能
 usagePage:"<blockquote><h3>このプレゼンツールの使い方</"+"h3><ul><li>← / → : 戻る / 進む<"+"/li><li>&lt; / &gt; : 文字を大きく / 小さく<"+"/li><li>5 : 文字を元の大きさにする<"+"/li><li>0 : 表紙に戻る<"+"/li><li>F11 : フルスクリーンモードにする<"+"/li><"+"/ul><"+"/blockquote>",
 restartPage:"<p style=\"text-align:center\">最後のスライドです<br /><a href=\"#\" onclick=\"CSSslide.restart();return false;\">表紙に戻る<"+"/a><"+"/p>",
 errorNotFoundDiv:"<p>エラー: &lt;div&gt; が見つかりません。<"+"/p>",
 //初期化
 init:function(){
  //イベントリスナの登録
  document.onkeydown=CSSslide.keyListener;
//  document.onmousedown=CSSslide.mouseListener;
  //使い方のページ
  if(CSSslide.enableUsagePage==1){
   CSSslide.slide.push({"innerHTML": CSSslide.usagePage,"s":null,"c":null});
  }
  //ページ取得
  var slides=document.getElementsByTagName("div");
  for(var i=0;i<slides.length;i++){
   CSSslide.slide.push({"innerHTML":slides[i].innerHTML,"s":slides[i].getAttribute("style"),"c":slides[i].getAttribute("class")});
  }
  //DIV がなかったとき
  if(CSSslide.slide.length<=1){ CSSslide.slide.push({"innerHTML":CSSslide.errorNotFoundDiv,"s":null,"c":null}); }
  //最初に戻るページ
  if(CSSslide.enableRestartPage==1){
   CSSslide.slide.push({"innerHTML": CSSslide.restartPage,"s":null,"c":null});
  }
  //スライドを作る
  document.body.innerHTML="";
  var slideElem=document.createElement("div");
  slideElem.id="slide";
  slideElem.innerHTML=CSSslide.slide[0].innerHTML;
  document.body.appendChild(slideElem);
  //フォントサイズを自動調整
  if(CSSslide.enableFontResize){
   CSSslide.fontSizeRatioDefault=Math.ceil(100+(document.documentElement.offsetWidth*0.14));
   CSSslide.fontResize(CSSslide.fontSizeRatioDefault);
  }
 },
 //文字の大きさを変更する。
 fontResize:function(s){
   CSSslide.fontSizeRatio=s;
   document.body.style.fontSize=s+"%";
 },
 //進む・戻る
 move:function(n){
  if(CSSslide.enableLoop&&n>=CSSslide.slide.length){ n=0; }
  else if(CSSslide.enableLoop&&n<0){ n=CSSslide.slide.length-1; }
  if(n>=0&&n<CSSslide.slide.length){
   CSSslide.nowPage=n;
   CSSslide.gID('slide').innerHTML=CSSslide.slide[n].innerHTML;
   CSSslide.gID('slide').setAttribute('style',CSSslide.slide[n].s);
   CSSslide.gID('slide').setAttribute('class',CSSslide.slide[n].c);
  }
 },
 //最初から開始
 restart:function(){ (CSSslide.enableUsagePage==1)?CSSslide.move(1):CSSslide.move(0);  },
 //キーが押されたとき
 keyListener:function(evt){
  var kc=(document.all)?event.keyCode:evt.keyCode;
  if(kc==39||kc==13){ CSSslide.move(CSSslide.nowPage+1); }
  else if(kc==37){ CSSslide.move(CSSslide.nowPage-1); }
  else if(kc==188){ CSSslide.fontResize(CSSslide.fontSizeRatio-10);  }
  else if(kc==190){ CSSslide.fontResize(CSSslide.fontSizeRatio+10);  }
  else if(kc==53||kc==101){ CSSslide.fontResize(CSSslide.fontSizeRatioDefault);  }
  else if(kc==48||kc==96){ CSSslide.restart();  }
  else;
 },
 //マウスが押されたとき
 mouseListener:function(evt){
  var bt=(document.all)?(event.button==1?0:(event.button==4)?1:event.button):evt.button;
  if(bt==0){ CSSslide.move(CSSslide.nowPage+1); }
 },
 //aka.$-function
 gID:function(id){ return(document.getElementById(id)); }
};

window.onload = CSSslide.init;
