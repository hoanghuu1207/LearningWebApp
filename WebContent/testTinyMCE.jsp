<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>

<h1>The textarea element</h1>

<form action="/tiny-mce" method="POST" enctype="multipart/form-data">
  <p><label for="w3review">Review of W3Schools:</label></p>
  <textarea class="textarea-mce" id="w3review" name="content" rows="4" cols="50">At w3schools.com you will learn how to make a website. They offer free tutorials in all web development technologies.</textarea>
  <br>
  <input type="submit" value="Submit">
</form>

<script src="https://cdn.tiny.cloud/1/lhhxxq7tqiq7nrjsovfwjleh1ops03bkr5qmm1n1jgljkuli/tinymce/7/tinymce.min.js" referrerpolicy="origin"></script>
<script>
    tinymce.init({
      selector: "textarea.textarea-mce",
    	plugins: 'image',
    	file_picker_callback: function (cb, value, meta) {
        var input = document.createElement('input');
        input.setAttribute('type', 'file');
        input.setAttribute('accept', 'image/*');

        input.onchange = function () {
          var file = this.files[0];

          var reader = new FileReader();
          reader.onload = function () {
            var id = 'blobid' + (new Date()).getTime();
            var blobCache =  tinymce.activeEditor.editorUpload.blobCache;
            var base64 = reader.result.split(',')[1];
            var blobInfo = blobCache.create(id, file, base64);
            blobCache.add(blobInfo);

            cb(blobInfo.blobUri(), { title: file.name });
          };
          reader.readAsDataURL(file);
        };

        input.click();
      },
    });
</script>
</body>
</html>
