<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <style type="text/css">
                    body {background-color:rgb(84,84,84);
                    color:white;}
                    .requisition-notification{ text-align: center;}
                    table {
                    border-collapse: separate;
                    border-spacing: 2em 1em;
                    }
                    h1, .footer{
                    color: rgb(245,123,32);
                    }
                </style>
            </head>
            <body class="requisition-notification">
                <img src="https://drive.google.com/uc?id=0Bysr99hpvJ3hZHljb0ZUQW15a1U" height="20%" width="30%"/>
                <h1> Notificación de solicitud de productos </h1>
                <h4>Existe un nuevo producto en la categoría a la que esta suscrito, acá los detalles de la solicitud:</h4>

                <table align="center">
                    <tr>
                        <th> Categoría </th>
                        <th> Descripción </th>
                        <th> Cantidad </th>
                        <th> Dirección </th>
                        <th> Fecha límite </th>
                        <th> Precio base </th>
                    </tr>

                    <xsl:for-each select="requisitionInfo">
                        <tr align="center">
                            <td class="column"><xsl:value-of select="title"/></td>
                            <td class="column"><xsl:value-of select="description"/></td>
                            <td class="column"><xsl:value-of select="quantity"/></td>
                            <td class="column"><xsl:value-of select="shipping_address"/></td>
                            <td class="column"><xsl:value-of select="limit_date"/></td>
                            <td class="column"><xsl:value-of select="base_amount"/> </td>
                        </tr>
                    </xsl:for-each>
                </table>
                <p> Gracias por usar Smartquote</p>
            </body>

            <footer>
                <div class="footer">
                    <h4>Pernix-Labs</h4>
                </div>
            </footer>
        </html>
    </xsl:template>
</xsl:stylesheet>