const path = require('path');
const CopyPlugin = require('copy-webpack-plugin')
const {TsconfigPathsPlugin} = require("tsconfig-paths-webpack-plugin");

module.exports = {
    mode: 'production',
    entry: {
        cart: './src/pages/cart/cart.tsx',
        store: './src/pages/store/store.tsx',
        product: './src/pages/product/product.tsx',
    },
    watchOptions: {
        aggregateTimeout: 25000,
        poll: 30000,
        ignored: ['**/node_modules', '/node_modules/', 'node_modules']
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
        ],

    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
        plugins: [
            new TsconfigPathsPlugin()
        ]
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist'),
    },
    plugins: [
        new CopyPlugin({
            patterns: [
                {
                    from: path.resolve(__dirname, 'dist'),
                    to: '../../target/classes/static/javascript',
                    noErrorOnMissing: true
                }
            ]
        })
    ]
};