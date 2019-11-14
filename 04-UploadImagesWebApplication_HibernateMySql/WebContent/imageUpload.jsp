<form action="ImageUpload?param=filesUpload" method ="post" enctype="multipart/form-data">

Select images <input type="file" name ="file" multiple="multiple">
<input type="submit" value="Upload">
<br>
<a href="${pageContext.request.contextPath}/ImageUpload?param=listingImages">List Images</a>
</form>