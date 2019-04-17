<#import "../layout.ftlh" as layout>

<@layout.default>
<form method="post">

    <div th:each="interest : ${interest}" class="checkbox">
        <input type="checkbox" name="interestIds" value="${interest.id}" id="${interest.id}"/>
        <label for="${interest.id}" name="${interest.name}"></label>
        <br />
    </div>

    <input type="submit" value="Remove Point of Interest" />
</form>
</@layout.default>